package itxj.ymb.config;

import itxj.ymb.annotation.NoAuth;
import itxj.ymb.constant.APIConstant;
import itxj.ymb.constant.CommonConstant;
import itxj.ymb.exception.AppException;
import itxj.ymb.mapper.UserMapper;
import itxj.ymb.pojo.User;
import itxj.ymb.util.DataUtils;
import itxj.ymb.util.RedisManager;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.TokenVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP配置
 */
@Configuration
@Aspect
public class AOPConfig {
	private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);
	@Resource
	private RedisManager redisManager;
	@Resource
	private UserMapper userMapper;
	@Resource
	private YmbApplicationConfig ymbApplicationConfig;

	/**
	 * 记录业务请求的时间
	 */
	private long req;

	/**
	 * 注解切入点
	 */
	@Pointcut("@annotation(itxj.ymb.annotation.ApiLog)")
	public void annotationPointcut() {
	}

	/**
	 * 控制器切入点
	 */
	@Pointcut("execution(* itxj.ymb..controller.*.*(..))")
	public void controllerPointcut() {
	}

	/**
	 * 打印请求日志
	 *
	 * @param joinpoint JoinPoint
	 */
	@Before("annotationPointcut()")
	public void printRequestDatagram(JoinPoint joinpoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = getIpAddress(request);
		req = System.currentTimeMillis();
		Object[] args = joinpoint.getArgs();
		LOGGER.debug("\n==> 拦截到请求："
				+ "\n==> 请求者IP：" + ip + " "
				+ "\n==> 请求时间：" + DataUtils.convertCurrentTimeToString()
				+ "\n==> 请求接口：" + request.getRequestURL()
				+ "\n==> 请求方法：" + request.getMethod()
				+ "\n==> 参数内容：" + Arrays.toString(args));
	}

	/**
	 * 打印响应日志
	 *
	 * @param joinPoint ProceedingJoinPoint
	 * @return Object
	 * @throws Throwable ProceedingJoinPoint
	 */
	@Around("annotationPointcut()")
	public Object printResponseDatagram(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result;
		result = joinPoint.proceed();
		long respTime = System.currentTimeMillis() - req;
		String d = String.valueOf(respTime);
		LOGGER.debug("\n<== 响应请求"
				+ "\n<== 请求耗时：" + Double.parseDouble(d) / 1000 + "s "
				+ "\n<== 响应内容：" + result);
		return result;
	}

	/**
	 * 接口权限校验
	 */
	@Around(value = "controllerPointcut()")
	public ResponseEntity<?> handleAuth(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Map<String, Object> map;
		LOGGER.debug("===进行接口权限校验");
		ResponseEntity<?> result = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// 拿请求地址
		String requestURL = request.getRequestURL().toString();
		// 将请求地址中除了上下文地址外的第一个路径拿到
		String[] split = requestURL.split("/");
		requestURL = split[4];
		// 从配置文件中读取允许不认证就可以访问的权限列表
		String[] permitUrls = ymbApplicationConfig.getPermitUrls();
		if (permitUrls != null && permitUrls.length > 0) {
			for (String permitUrl : permitUrls) {
				if (requestURL.equals(permitUrl)) {
					// 放行
					result = passAuth(proceedingJoinPoint);
				} else {
					// 进行权限校验
					map = checkAuth(proceedingJoinPoint, request);
					result = (ResponseEntity<?>) map.get("result");
				}
			}
		} else {
			// 进行权限校验
			map = checkAuth(proceedingJoinPoint, request);
			result = (ResponseEntity<?>) map.get("result");
		}
		return result;
	}

	/**
	 * 检查权限
	 *
	 * @param proceedingJoinPoint ProceedingJoinPoint
	 * @param request             HttpServletRequest
	 * @return Map<String, Object>
	 * @throws Throwable Throwable
	 */
	private Map<String, Object> checkAuth(ProceedingJoinPoint proceedingJoinPoint, HttpServletRequest request) throws Throwable {
		Map<String, Object> map = new HashMap<>();
		String refreshToken = null, accessToken = null;
		ResponseEntity<?> result;
		NoAuth noAuth = findMethod(proceedingJoinPoint).getAnnotation(NoAuth.class);
		if (noAuth == null) {
			// 从header中拿refreshToken和accessToken
			TokenVO tokenVO = new TokenVO(request);
			refreshToken = tokenVO.getRefreshToken();
			accessToken = tokenVO.getAccessToken();
			// 判断accessToken是否过期，如果过期则判断refreshToken是否过期
			if (redisManager.isExpire(accessToken)) {
				// 如果过期则让用户重新登录
				LOGGER.warn("===accessToken过期");
				if (redisManager.isExpire(refreshToken)) {
					LOGGER.warn("===refreshToken过期");
					result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				} else {
					// 校验未过期的refreshToken
					// 取出refreshToken中存储的用户信息
					User user = DataUtils.queryUserInfoByToken(userMapper, redisManager, refreshToken);
					String account = user.getAccount();
					String password = user.getPassword();
					boolean isValidRefreshToken = DataUtils.verifyToken(password, refreshToken, account);
					LOGGER.debug("===校验refreshToken");
					if (isValidRefreshToken) {
						// 删除旧的accessToken
						LOGGER.debug("===清理旧的refreshToken");
						DataUtils.deleteRedisTokenInfo(redisManager, refreshToken, null);
						LOGGER.debug("===正在重新生成双token");
						// 如果没有过期则生成新的refreshToken和accessToken，返回给前端来重新调用
						result = new Result<TokenVO>().generateResponseEntity(APIConstant.FORBIDDEN, DataUtils.generateTokenVO(redisManager,
								account,
								password,
								DataUtils.generateUserInfo(user)));
					} else {
						LOGGER.debug("===refreshToken校验不通过，权限校验失败");
						result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}
			} else {
				// 如果没有过期则校验accessToken
				LOGGER.debug("===校验accessToken");
				User user = DataUtils.queryUserInfoByToken(userMapper, redisManager, accessToken);
				boolean isValidAccessToken = DataUtils.verifyToken(null, accessToken, user.getAccount());
				if (isValidAccessToken) {
					LOGGER.debug("===accessToken校验通过，接口放行");
					result = (ResponseEntity<?>) proceedingJoinPoint.proceed();
				} else {
					LOGGER.debug("===accessToken校验不通过，权限校验失败");
					DataUtils.deleteRedisTokenInfo(redisManager, refreshToken, accessToken);
					result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
		} else {
			result = passAuth(proceedingJoinPoint);
		}
		map.put("result", result);
		map.put("refreshToken", refreshToken);
		map.put("accessToken", accessToken);
		return map;
	}

	/**
	 * 放通权限
	 *
	 * @param proceedingJoinPoint ProceedingJoinPoint
	 * @return 返回ResponseEntity
	 * @throws Throwable Throwable
	 */
	private ResponseEntity<?> passAuth(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ResponseEntity<?> result;
		LOGGER.debug("===该接口无需进行权限校验");
		result = (ResponseEntity<?>) proceedingJoinPoint.proceed();
		return result;
	}

	/**
	 * 获取控制器方法
	 *
	 * @param proceedingJoinPoint ProceedingJoinPoint
	 * @return Method
	 */
	private Method findMethod(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
		Signature signature = proceedingJoinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method == null) {
			throw new AppException("方法名未找到");
		}
		return method;
	}

	/**
	 * 获取请求IP地址
	 *
	 * @param request HttpServletRequest
	 * @return 返回发送请求的IP地址
	 */
	private String getIpAddress(HttpServletRequest request) {
		final String UNKNOWN = "unknown";
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}

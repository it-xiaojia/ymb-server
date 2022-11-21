package itxj.ymb.service;

import itxj.ymb.constant.AccountStatusConstant;
import itxj.ymb.constant.CommonConstant;
import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.dto.auth.ListQueryParam;
import itxj.ymb.dto.user.AccessCredential;
import itxj.ymb.dto.user.LoginParam;
import itxj.ymb.dto.user.PasswordParam;
import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.UserMapper;
import itxj.ymb.pojo.User;
import itxj.ymb.util.DataUtil;
import itxj.ymb.util.RedisManager;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.role.RoleInfoResult;
import itxj.ymb.vo.user.LoginCredential;
import itxj.ymb.vo.user.UserInfoResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 用户业务
 */
@Service
@Transactional
public class UserService {
	private static final Logger LOGGER = LogManager.getLogger(CommonConstant.LOGGER_NAME);
	@Resource
	private UserMapper userMapper;
	@Resource
	private RedisManager redisManager;
	@Resource
	private AuthService authService;

	/**
	 * 登录-获取接口请求权限
	 *
	 * @param loginParam 登录参数
	 * @return 登录成功返回refreshToken和accessToken
	 */
	public LoginCredential login(LoginParam loginParam) {
		User user = userMapper.findUserByObject(User.builder()
				.account(loginParam.getAccount())
				.password(loginParam.getPassword())
				.build());
		if (user == null) {
			throw new AppRuntimeException("登录失败，账号或密码错误");
		}
		Integer accountStatusCode = user.getStatusCode();
		// 账号被封禁
		if (accountStatusCode.equals(AccountStatusConstant.BAN.getCode())) {
			throw new AppRuntimeException("该账号存在违规行为，已被封禁，请联系管理员");
		}
		// 如果已登录，抛出异常，禁止用户二次登录
		if (accountStatusCode.equals(AccountStatusConstant.IS_LOGIN.getCode())) {
			throw new AppRuntimeException("此账号已登录，不可以重复登录");
		}
		// 如果用户没有登录，修改用户状态码为已登录，并记录成功登录时间
		userMapper.updateUser(User.builder()
				.id(user.getId())
				.lastLoginTime(DataUtil.convertCurrentTimeToString())
				.statusCode(AccountStatusConstant.IS_LOGIN.getCode())
				.build());
		// 将USER_CREDENTIAL_REDIS_KEY+用户ID为key，用户ID@系统当前时间戳和UUID拼接串进行md5加密为value存入redis中，并永不过期
		String value = user.getId() + CommonConstant.PUBLIC_SPLIT + System.currentTimeMillis() + UUID.randomUUID();
		redisManager.set(CommonConstant.USER_CREDENTIAL_REDIS_KEY + user.getId(), DataUtil.stringToMd5(value));
		// 将value响应给客户端，作为在没有token的情况下调用敏感接口的凭证
		// 根据用户账户和密码信息生成鉴权使用的双token
		TokenVO tokenVO = DataUtil.generateTokenVO(redisManager, user.getAccount(), user.getPassword(), DataUtil.generateUserInfo(user));
		// 返回用户的登录凭证
		return new LoginCredential(value, tokenVO.getRefreshToken(), tokenVO.getAccessToken());
	}

	/**
	 * 获取用户信息
	 *
	 * @param queryParam 查询参数
	 * @return 返回用户对象
	 */
	public UserInfoResult getUserInfo(BeanQueryParam queryParam, TokenVO tokenVO) {
		// 当传入的用户ID为0的时候，查询当前登录用户，否则根据ID查询指定的用户
		User user = (queryParam.getId() == 0) ?
				DataUtil.getUserInfoByToken(userMapper, redisManager, tokenVO.getAccessToken()) :
				userMapper.findUserById(queryParam.getId());
		if (user == null) {
			throw new AppRuntimeException("无此用户信息");
		}
		UserInfoResult userInfoResult = new UserInfoResult(user);
		userInfoResult.setAuthMenuList(authService.getAuthList(ListQueryParam.builder().roleId(user.getRole().getId()).build()));
		userInfoResult.setUserRole(new RoleInfoResult(user.getRole()));
		return userInfoResult;
	}

	/**
	 * 修改用户密码
	 *
	 * @param passwordParam 密码参数
	 */
	public void updateUserPassword(PasswordParam passwordParam, TokenVO tokenVO) {
		Integer userId = passwordParam.getUserId();
		String password = DataUtil.stringToMd5(passwordParam.getPassword());
		userMapper.updateUser(User.builder()
				.id(userId)
				.password(password)
				.build());
		// 密码修改成功后调用exitLogin方法清除用户的登录信息
		exitLogin(tokenVO);
	}

	/**
	 * 退出登录-删除redis中的用户token信息并删除session中的用户信息
	 *
	 * @param tokenVO 传入的双token，其中refreshToken用来获取用户ID进行修改用户登录状态
	 */
	public void exitLogin(TokenVO tokenVO) {
		String[] split = redisManager.get(tokenVO.getRefreshToken()).split(CommonConstant.PUBLIC_SPLIT);
		Integer userId = Integer.parseInt(split[0]);
		// 修改用户登录状态
		userMapper.updateUser(User.builder()
				.id(userId)
				.statusCode(AccountStatusConstant.NO_LOGIN.getCode())
				.build());
		// 删除redis中的token信息
		DataUtil.deleteRedisTokenInfo(redisManager, tokenVO.getRefreshToken(), tokenVO.getAccessToken());
		// 从redis中删除访问凭证
		String key = CommonConstant.USER_CREDENTIAL_REDIS_KEY + userId;
		if (!redisManager.isExpire(key)) {
			redisManager.delete(key);
			LOGGER.debug("成功删除redis中的访问凭证");
		}
	}

	/**
	 * 重置用户状态为未登录
	 *
	 * @param credential 接口访问凭证
	 */
	public void resetUserDBStatus(AccessCredential credential) {
		// 校验用户传入的凭证是否有效
		String[] split = credential.getCredential().split(CommonConstant.PUBLIC_SPLIT);
		if (split.length != 2) {
			throw new AppRuntimeException("访问凭证格式错误");
		}
		Integer userId = Integer.parseInt(split[0]);
		String key = CommonConstant.USER_CREDENTIAL_REDIS_KEY + userId;
		String redisCredential = redisManager.get(key);
		if (!DataUtil.stringToMd5(credential.getCredential()).equals(redisCredential)) {
			throw new AppRuntimeException("访问凭证校验失败");
		}
		// 更新数据库中的状态
		userMapper.updateUser(User.builder()
				.id(userId)
				.statusCode(AccountStatusConstant.NO_LOGIN.getCode())
				.build());
		// 从redis中删除访问凭证
		redisManager.delete(key);
	}
}

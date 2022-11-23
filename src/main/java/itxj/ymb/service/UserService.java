package itxj.ymb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.constant.AccountStatusConstant;
import itxj.ymb.constant.CommonConstant;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.auth.AuthListQueryParam;
import itxj.ymb.dto.user.*;
import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.UserMapper;
import itxj.ymb.pojo.User;
import itxj.ymb.util.DataUtils;
import itxj.ymb.util.RedisManager;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.auth.AuthVO;
import itxj.ymb.vo.user.LoginCredential;
import itxj.ymb.vo.user.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
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
	private RoleService roleService;
	@Resource
	private AuthService authService;

	@Resource
	private RedisManager redisManager;

	public UserVO queryObject(ObjectOperateParam queryParam) {
		Integer id = queryParam.getId();
		User user = userMapper.selectById(id);
		if (ObjectUtils.isEmpty(user)) {
			throw new AppRuntimeException("无此用户信息");
		}
		UserVO userInfoResult = new UserVO(user);
		userInfoResult.setUser(user);
		return userInfoResult;
	}

	public Page<UserVO> queryPage(UserPageQueryParam queryParam) {
		return null;
	}

	public void add(UserAddParam addParam) {

	}

	public void update(UserUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}

	/**
	 * 登录-获取接口请求权限
	 *
	 * @param loginParam 登录参数
	 * @return 登录成功返回refreshToken和accessToken
	 */
	public LoginCredential login(LoginParam loginParam) {
		String account = loginParam.getAccount();
		String password = loginParam.getPassword();
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.eq("val_account", account);
		userQueryWrapper.eq("val_password", password);
		User user = userMapper.selectOne(userQueryWrapper);
		if (user == null) {
			throw new AppRuntimeException("登录失败，账号或密码错误");
		}
		Integer accountStatusCode = user.getAccountStatusCode();
		// 账号被封禁
		if (accountStatusCode.equals(AccountStatusConstant.BAN.getCode())) {
			throw new AppRuntimeException("该账号存在违规行为，已被封禁，请联系管理员");
		}
		// 如果已登录，抛出异常，禁止用户二次登录
		if (accountStatusCode.equals(AccountStatusConstant.IS_LOGIN.getCode())) {
			throw new AppRuntimeException("此账号已登录，不可以重复登录");
		}
		// 如果用户没有登录，修改用户状态码为已登录，并记录成功登录时间
		userMapper.updateById(User.builder()
				.id(user.getId())
				.lastLoginTime(DataUtils.convertCurrentTimeToString())
				.accountStatusCode(AccountStatusConstant.IS_LOGIN.getCode())
				.build());
		// 将USER_CREDENTIAL_REDIS_KEY+用户ID为key，用户ID@系统当前时间戳和UUID拼接串进行md5加密为value存入redis中，并永不过期
		String value = user.getId() + CommonConstant.PUBLIC_SPLIT + System.currentTimeMillis() + UUID.randomUUID();
		redisManager.set(CommonConstant.USER_CREDENTIAL_REDIS_KEY + user.getId(), DataUtils.stringToMd5(value));
		// 将value响应给客户端，作为在没有token的情况下调用敏感接口的凭证
		// 根据用户账户和密码信息生成鉴权使用的双token
		TokenVO tokenVO = DataUtils.generateTokenVO(redisManager, user.getAccount(), user.getPassword(), DataUtils.generateUserInfo(user));
		// 返回用户的登录凭证
		return new LoginCredential(value, tokenVO.getRefreshToken(), tokenVO.getAccessToken());
	}

	/**
	 * 根据token查询主页用户信息
	 *
	 * @param tokenVO 传入的token对象
	 * @return 返回用户基本信息，用户角色名称，角色的一级权限和二级权限
	 */
	public UserVO queryIndexUserInfo(TokenVO tokenVO) {
		String accessToken = tokenVO.getAccessToken();
		User user = DataUtils.queryUserInfoByToken(userMapper, redisManager, accessToken);
		if (ObjectUtils.isEmpty(user)) {
			throw new AppRuntimeException("无此用户信息");
		}
		ObjectOperateParam objectOperateParam = new ObjectOperateParam();
		objectOperateParam.setId(user.getUserRoleId());
		String roleName = roleService.queryObject(objectOperateParam).getRole().getName();
		List<AuthVO> level1AuthList = authService.queryList(AuthListQueryParam.builder().roleId(user.getUserRoleId()).build());
		Integer parentAuthId = level1AuthList.get(1).getAuth().getId();
		List<AuthVO> level2AuthList = authService.queryList(AuthListQueryParam.builder().roleId(user.getUserRoleId()).parentAuthId(parentAuthId).build());
		UserVO userInfoResult = new UserVO(user);
		userInfoResult.setUser(user);
		userInfoResult.setRoleName(roleName);
		userInfoResult.setLevel1AuthList(level1AuthList);
		userInfoResult.setLevel2AuthList(level2AuthList);
		return userInfoResult;
	}

	/**
	 * 修改用户密码
	 *
	 * @param passwordParam 密码参数
	 */
	public void updatePassword(PasswordParam passwordParam, TokenVO tokenVO) {
		Integer userId = passwordParam.getUserId();
		String password = DataUtils.stringToMd5(passwordParam.getPassword());
		userMapper.updateById(User.builder()
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
		userMapper.updateById(User.builder()
				.id(userId)
				.accountStatusCode(AccountStatusConstant.NO_LOGIN.getCode())
				.build());
		// 删除redis中的token信息
		DataUtils.deleteRedisTokenInfo(redisManager, tokenVO.getRefreshToken(), tokenVO.getAccessToken());
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
	public void resetDBStatus(AccessCredential credential) {
		// 校验用户传入的凭证是否有效
		String[] split = credential.getCredential().split(CommonConstant.PUBLIC_SPLIT);
		if (split.length != 2) {
			throw new AppRuntimeException("访问凭证格式错误");
		}
		Integer userId = Integer.parseInt(split[0]);
		String key = CommonConstant.USER_CREDENTIAL_REDIS_KEY + userId;
		String redisCredential = redisManager.get(key);
		if (!DataUtils.stringToMd5(credential.getCredential()).equals(redisCredential)) {
			throw new AppRuntimeException("访问凭证校验失败");
		}
		// 更新数据库中的状态
		userMapper.updateById(User.builder()
				.id(userId)
				.accountStatusCode(AccountStatusConstant.NO_LOGIN.getCode())
				.build());
		// 从redis中删除访问凭证
		redisManager.delete(key);
	}
}

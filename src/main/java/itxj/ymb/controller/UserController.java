package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.annotation.NoAuth;
import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.dto.user.AccessCredential;
import itxj.ymb.dto.user.LoginParam;
import itxj.ymb.dto.user.PasswordParam;
import itxj.ymb.service.UserService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.user.LoginCredential;
import itxj.ymb.vo.user.UserInfoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private HttpServletRequest request;

	@PostMapping("login")
	@ApiLog
	@NoAuth
	public ResponseEntity<LoginCredential> login(@RequestBody @Validated LoginParam loginParam) {
		return new Result<LoginCredential>().generateSuccessResponseEntity("登录成功", userService.login(loginParam));
	}

	@PostMapping("getUserInfo")
	@ApiLog
	public ResponseEntity<UserInfoResult> getUserInfo(@RequestBody @Validated BeanQueryParam queryParam) {
		return new Result<UserInfoResult>().generateSuccessResponseEntity("获取用户信息成功", userService.getUserInfo(queryParam, new TokenVO(request)));
	}

	@PostMapping("updateUserPassword")
	@ApiLog
	public ResponseEntity<?> updateUserPassword(@RequestBody @Validated PasswordParam passwordParam) {
		userService.updateUserPassword(passwordParam, new TokenVO(request));
		return new Result<>().generateSuccessResponseEntity("修改用户密码成功");
	}

	@PostMapping("exitLogin")
	@ApiLog
	public ResponseEntity<?> exitLogin() {
		userService.exitLogin(new TokenVO(request));
		return new Result<>().generateSuccessResponseEntity("成功退出登录");
	}

	@PostMapping("resetUserDBStatus")
	@ApiLog
	@NoAuth
	public ResponseEntity<?> resetUserDBStatus(@RequestBody @Validated AccessCredential credential) {
		userService.resetUserDBStatus(credential);
		return new Result<>().generateSuccessResponseEntity("成功重置数据库中的用户状态");
	}
}

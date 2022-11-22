package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.annotation.NoAuth;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.user.*;
import itxj.ymb.service.UserService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.user.LoginCredential;
import itxj.ymb.vo.user.UserVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<UserVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		UserVO userInfoResult = userService.queryObject(queryParam);
		return new Result<UserVO>().generateSuccessResponseEntity(userInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated UserPageQueryParam queryParam) {
		List<PageResult> userInfoResultList = userService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(userInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated UserAddParam addParam) {
		userService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated UserUpdateParam updateParam) {
		userService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		userService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("login")
	@ApiLog
	@NoAuth
	public ResponseEntity<LoginCredential> login(@RequestBody @Validated LoginParam loginParam) {
		LoginCredential loginCredential = userService.login(loginParam);
		return new Result<LoginCredential>().generateSuccessResponseEntity(loginCredential);
	}

	@PostMapping("queryIndexUserInfo")
	@ApiLog
	public ResponseEntity<UserVO> queryIndexUserInfo() {
		TokenVO tokenVO = new TokenVO(request);
		UserVO userInfoResult = userService.queryIndexUserInfo(tokenVO);
		return new Result<UserVO>().generateSuccessResponseEntity(userInfoResult);
	}

	@PostMapping("updatePassword")
	@ApiLog
	public ResponseEntity<?> updatePassword(@RequestBody @Validated PasswordParam passwordParam) {
		TokenVO tokenVO = new TokenVO(request);
		userService.updatePassword(passwordParam, tokenVO);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("exitLogin")
	@ApiLog
	public ResponseEntity<?> exitLogin() {
		TokenVO tokenVO = new TokenVO(request);
		userService.exitLogin(tokenVO);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("resetDBStatus")
	@ApiLog
	@NoAuth
	public ResponseEntity<?> resetDBStatus(@RequestBody @Validated AccessCredential credential) {
		userService.resetDBStatus(credential);
		return new Result<>().generateSuccessResponseEntity();
	}
}

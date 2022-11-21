package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.dto.auth.AddParam;
import itxj.ymb.dto.auth.DeleteParam;
import itxj.ymb.dto.auth.ListQueryParam;
import itxj.ymb.dto.auth.UpdateParam;
import itxj.ymb.service.AuthService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.auth.AuthInfoResult;
import itxj.ymb.vo.user.AuthMenu;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("auth")
public class AuthController {
	@Resource
	private AuthService authService;

	@PostMapping("getAuthList")
	@ApiLog
	public ResponseEntity<List<AuthMenu>> getAuthList(@RequestBody @Validated ListQueryParam queryParam) {
		return new Result<List<AuthMenu>>().generateSuccessResponseEntity("权限列表查询成功", authService.getAuthList(queryParam));
	}

	@PostMapping("getAuthById")
	@ApiLog
	public ResponseEntity<AuthInfoResult> getAuthById(@RequestBody @Validated BeanQueryParam queryParam) {
		return new Result<AuthInfoResult>().generateSuccessResponseEntity("权限查询成功", authService.getAuthById(queryParam));
	}

	@PostMapping("addAuth")
	@ApiLog
	public ResponseEntity<?> addAuth(@RequestBody @Validated AddParam addParam) {
		authService.addAuth(addParam);
		return new Result<>().generateSuccessResponseEntity("成功新增一个权限");
	}

	@PostMapping("updateAuth")
	@ApiLog
	public ResponseEntity<?> updateAuth(@RequestBody @Validated UpdateParam updateParam) {
		authService.updateAuth(updateParam);
		return new Result<>().generateSuccessResponseEntity("成功更新一个权限");
	}

	@PostMapping("deleteAuth")
	@ApiLog
	public ResponseEntity<?> deleteAuth(@RequestBody @Validated DeleteParam deleteParam) {
		authService.deleteAuth(deleteParam);
		return new Result<>().generateSuccessResponseEntity("成功删除一个权限");
	}
}

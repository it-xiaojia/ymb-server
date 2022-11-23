package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.auth.AuthAddParam;
import itxj.ymb.dto.auth.AuthListQueryParam;
import itxj.ymb.dto.auth.AuthUpdateParam;
import itxj.ymb.service.AuthService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.auth.AuthVO;
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

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<AuthVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		AuthVO authInfoResult = authService.queryObject(queryParam);
		return new Result<AuthVO>().generateSuccessResponseEntity(authInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<AuthVO>> queryList(@RequestBody @Validated AuthListQueryParam queryParam) {
		List<AuthVO> authVOList = authService.queryList(queryParam);
		return new Result<List<AuthVO>>().generateSuccessResponseEntity(authVOList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated AuthAddParam addParam) {
		authService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated AuthUpdateParam updateParam) {
		authService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		authService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

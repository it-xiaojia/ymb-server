package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.auth.AddParam;
import itxj.ymb.dto.auth.ListQueryParam;
import itxj.ymb.dto.auth.UpdateParam;
import itxj.ymb.service.AuthService;
import itxj.ymb.vo.PageResult;
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
	public ResponseEntity<AuthVO> queryObject(@RequestBody @Validated ObjectQueryParam queryParam) {
		AuthVO authInfoResult = authService.queryObject(queryParam);
		return new Result<AuthVO>().generateSuccessResponseEntity(authInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated ListQueryParam queryParam) {
		List<PageResult> authInfoResultList = authService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(authInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated AddParam addParam) {
		authService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated UpdateParam updateParam) {
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
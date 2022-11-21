package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.role.AddParam;
import itxj.ymb.dto.role.ListQueryParam;
import itxj.ymb.dto.role.UpdateParam;
import itxj.ymb.service.RoleService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.role.RoleVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("role")
public class RoleController {
	@Resource
	private RoleService roleService;

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<RoleVO> queryObject(@RequestBody @Validated ObjectQueryParam queryParam) {
		RoleVO roleInfoResult = roleService.queryObject(queryParam);
		return new Result<RoleVO>().generateSuccessResponseEntity(roleInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated ListQueryParam queryParam) {
		List<PageResult> roleInfoResultList = roleService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(roleInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated AddParam addParam) {
		roleService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated UpdateParam updateParam) {
		roleService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		roleService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

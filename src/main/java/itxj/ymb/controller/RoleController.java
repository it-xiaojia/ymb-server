package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.role.RoleAddParam;
import itxj.ymb.dto.role.RolePageQueryParam;
import itxj.ymb.dto.role.RoleUpdateParam;
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
	public ResponseEntity<RoleVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		RoleVO roleInfoResult = roleService.queryObject(queryParam);
		return new Result<RoleVO>().generateSuccessResponseEntity(roleInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<PageResult<RoleVO>> queryList(@RequestBody @Validated RolePageQueryParam queryParam) {
		PageResult<RoleVO> roleInfoResultList = roleService.queryList(queryParam);
		return new Result<PageResult<RoleVO>>().generateSuccessResponseEntity(roleInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated RoleAddParam addParam) {
		roleService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated RoleUpdateParam updateParam) {
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

package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.annotation.NoAuth;
import itxj.ymb.service.RoleService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.role.RoleInfoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("getRoleList")
	@ApiLog
	@NoAuth
	public ResponseEntity<List<RoleInfoResult>> getRoleList() {
		return new Result<List<RoleInfoResult>>().generateSuccessResponseEntity("角色列表查询成功", roleService.getRoleList());
	}
}

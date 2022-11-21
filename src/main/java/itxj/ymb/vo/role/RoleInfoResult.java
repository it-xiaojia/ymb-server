package itxj.ymb.vo.role;

import itxj.ymb.pojo.Role;

import java.io.Serializable;

/**
 * 角色信息结果，不对前端展示查询角色拥有的权限列表
 */
public class RoleInfoResult extends Role implements Serializable {
	public RoleInfoResult(Role role) {
		super(role.getId(), role.getName(), null);
	}
}

package itxj.ymb.mapper;

import itxj.ymb.pojo.Role;

import java.util.List;

/**
 * 角色数据接口
 */
public interface RoleMapper {
	/**
	 * 通过角色ID查询该角色拥有的角色信息
	 *
	 * @param roleId 角色ID
	 * @return 返回角色对象
	 */
	Role findRoleById(Integer roleId);

	/**
	 * 查询角色列表
	 *
	 * @return 返回角色列表
	 */
	List<Role> findRoleList();
}

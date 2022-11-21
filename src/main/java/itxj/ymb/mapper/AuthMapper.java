package itxj.ymb.mapper;

import itxj.ymb.pojo.Auth;

import java.util.List;

/**
 * 权限数据接口
 */
public interface AuthMapper {
	/**
	 * 查询权限列表，如果角色ID不为空，则查询指定角色拥有的权限列表
	 *
	 * @param roleId 角色ID
	 * @return 返回被查询角色的权限列表
	 */
	List<Auth> findAuthList(Integer roleId);

	/**
	 * 通过父权限ID查询当前父权限拥有的所有子权限
	 *
	 * @param parentAuthId 父权限ID
	 * @return 返回子权限列表
	 */
	List<Auth> findChildAuthByParentAuthId(Integer parentAuthId);

	/**
	 * 通过ID查询某一条权限
	 *
	 * @param id 权限ID
	 * @return 返回权限对象
	 */
	Auth findAuthById(Integer id);

	/**
	 * 新增权限
	 *
	 * @param auth 权限对象
	 */
	void addAuth(Auth auth);

	/**
	 * 删除权限
	 *
	 * @param id 权限ID
	 */
	void deleteAuth(Integer id);

	/**
	 * 更新权限
	 *
	 * @param auth 权限对象
	 */
	void updateAuth(Auth auth);
}

package itxj.ymb.service;

import itxj.ymb.mapper.RoleMapper;
import itxj.ymb.pojo.Role;
import itxj.ymb.vo.role.RoleInfoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色业务
 */
@Service
@Transactional
public class RoleService {
	@Resource
	private RoleMapper roleMapper;

	/**
	 * 获取角色列表
	 *
	 * @return 返回角色列表
	 */
	public List<RoleInfoResult> getRoleList() {
		List<Role> roleList = roleMapper.findRoleList();
		List<RoleInfoResult> targetList = new ArrayList<>();
		for (Role role : roleList) {
			targetList.add(new RoleInfoResult(role));
		}
		return targetList;
	}
}

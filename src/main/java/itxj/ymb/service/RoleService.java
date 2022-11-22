package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.role.RoleAddParam;
import itxj.ymb.dto.role.RolePageQueryParam;
import itxj.ymb.dto.role.RoleUpdateParam;
import itxj.ymb.mapper.RoleMapper;
import itxj.ymb.pojo.Role;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.role.RoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 角色业务
 */
@Service
@Transactional
public class RoleService {
	@Resource
	private RoleMapper roleMapper;

	public RoleVO queryObject(ObjectOperateParam queryParam) {
		Integer id = queryParam.getId();
		Role role = roleMapper.selectById(id);
		RoleVO roleVO = new RoleVO(role);
		roleVO.setRole(role);
		return roleVO;
	}

	public PageResult<RoleVO> queryList(RolePageQueryParam queryParam) {
		return null;
	}

	public void add(RoleAddParam addParam) {

	}

	public void update(RoleUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

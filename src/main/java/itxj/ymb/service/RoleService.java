package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.role.RoleAddParam;
import itxj.ymb.dto.role.RoleListQueryParam;
import itxj.ymb.dto.role.RoleUpdateParam;
import itxj.ymb.mapper.RoleMapper;
import itxj.ymb.pojo.Role;
import itxj.ymb.vo.role.RoleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
		return new RoleVO(role);
	}

	public List<RoleVO> queryList(RoleListQueryParam queryParam) {
		return null;
	}

	public void add(RoleAddParam addParam) {

	}

	public void update(RoleUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

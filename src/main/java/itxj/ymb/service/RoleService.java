package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.role.AddParam;
import itxj.ymb.dto.role.ListQueryParam;
import itxj.ymb.dto.role.UpdateParam;
import itxj.ymb.mapper.RoleMapper;
import itxj.ymb.vo.PageResult;
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

	public RoleVO queryObject(ObjectQueryParam queryParam) {
		return null;
	}

	public List<PageResult> queryList(ListQueryParam queryParam) {
		return null;
	}

	public void add(AddParam addParam) {

	}

	public void update(UpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

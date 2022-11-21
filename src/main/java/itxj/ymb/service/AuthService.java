package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.auth.AddParam;
import itxj.ymb.dto.auth.ListQueryParam;
import itxj.ymb.dto.auth.UpdateParam;
import itxj.ymb.mapper.AuthMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.auth.AuthVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限业务
 */
@Service
@Transactional
public class AuthService {
	@Resource
	private AuthMapper authMapper;

	public AuthVO queryObject(ObjectQueryParam queryParam) {
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

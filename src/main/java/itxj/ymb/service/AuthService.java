package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.auth.AuthAddParam;
import itxj.ymb.dto.auth.AuthPageQueryParam;
import itxj.ymb.dto.auth.AuthUpdateParam;
import itxj.ymb.mapper.AuthMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.auth.AuthVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 权限业务
 */
@Service
@Transactional
public class AuthService {
	@Resource
	private AuthMapper authMapper;

	public AuthVO queryObject(ObjectOperateParam queryParam) {
		return null;
	}

	public PageResult<AuthVO> queryList(AuthPageQueryParam queryParam) {

		return null;
	}

	public void add(AuthAddParam addParam) {

	}

	public void update(AuthUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.auth.AuthAddParam;
import itxj.ymb.dto.auth.AuthListQueryParam;
import itxj.ymb.dto.auth.AuthUpdateParam;
import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.AuthMapper;
import itxj.ymb.pojo.Auth;
import itxj.ymb.vo.auth.AuthVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限业务
 */
@Service
@Transactional
public class AuthService {
	@Resource
	private AuthMapper authMapper;

	public AuthVO queryObject(ObjectOperateParam queryParam) {
		Auth auth = authMapper.selectById(queryParam.getId());
		if (StringUtils.isEmpty(auth)) {
			throw new AppRuntimeException("查询的权限不存在");
		}
		return new AuthVO(auth);
	}

	public List<AuthVO> queryList(AuthListQueryParam queryParam) {
		List<AuthVO> authVOList = new ArrayList<>();
		List<Auth> authList = authMapper.customSelectList(queryParam);
		for (Auth auth : authList) {
			authVOList.add(new AuthVO(auth));
		}
		return authVOList;
	}

	public void add(AuthAddParam addParam) {

	}

	public void update(AuthUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

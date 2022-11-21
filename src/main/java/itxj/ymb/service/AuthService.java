package itxj.ymb.service;

import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.dto.auth.AddParam;
import itxj.ymb.dto.auth.DeleteParam;
import itxj.ymb.dto.auth.ListQueryParam;
import itxj.ymb.dto.auth.UpdateParam;
import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.AuthMapper;
import itxj.ymb.pojo.Auth;
import itxj.ymb.vo.auth.AuthInfoResult;
import itxj.ymb.vo.user.AuthMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限业务
 */
@Service
@Transactional
public class AuthService {
	@Resource
	private AuthMapper authMapper;

	/**
	 * 获取权限列表
	 *
	 * @param queryParam 查询参数
	 * @return 如果角色ID为0，返回全部权限列表，否则返回当前角色ID下的权限列表
	 */
	public List<AuthMenu> getAuthList(ListQueryParam queryParam) {
		List<Auth> authList = authMapper.findAuthList(queryParam.getRoleId());
		List<AuthMenu> authMenuList = new ArrayList<>();
		for (Auth auth : authList) {
			authMenuList.add(AuthMenu.builder()
					.key(auth.getId())
					.title(auth.getName())
					.parentId(auth.getParentAuthId() == null ? 0 : auth.getParentAuthId())
					.iconType(auth.getIconClass())
					.url(auth.getUrl())
					.build());
		}
		// 封装用户的菜单
		return authMenuList
				.stream()
				.filter(categoryEntity -> categoryEntity.getParentId() == 0)
				.peek(menu -> menu.setChildren(getChildren(menu, authMenuList)))
				.collect(Collectors.toList());
	}

	/**
	 * 封装子菜单
	 *
	 * @param root AuthMenu
	 * @param all  全部菜单项
	 * @return 返回多级菜单列表
	 */
	private List<AuthMenu> getChildren(AuthMenu root, List<AuthMenu> all) {
		return all.stream()
				.filter(categoryEntity -> categoryEntity.getParentId().equals(root.getKey()))
				.peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, all)))
				.collect(Collectors.toList());
	}

	/**
	 * 通过权限ID查询权限信息
	 *
	 * @param queryParam 查询参数
	 * @return 返回权限对象
	 */
	public AuthInfoResult getAuthById(BeanQueryParam queryParam) {
		return new AuthInfoResult(authMapper.findAuthById(queryParam.getId()));
	}

	/**
	 * 新增权限
	 *
	 * @param addParam 权限新增参数
	 */
	public void addAuth(AddParam addParam) {
		authMapper.addAuth(Auth.builder()
				.name(addParam.getName())
				.url(addParam.getUrl())
				.iconClass(addParam.getIconClass())
				.parentAuthId(addParam.getParentAuthId())
				.build());
		// 如果父权限ID不为空，则更新父权限的val_has_child字段为1
		if (addParam.getParentAuthId() != null && addParam.getParentAuthId() != 0) {
			authMapper.updateAuth(Auth.builder()
					.id(addParam.getParentAuthId())
					.hasChild(1)
					.build());
		}
	}

	/**
	 * 修改权限
	 *
	 * @param updateParam 权限修改参数
	 */
	public void updateAuth(UpdateParam updateParam) {
		authMapper.updateAuth(Auth.builder()
				.name(updateParam.getName())
				.url(updateParam.getUrl())
				.parentAuthId(updateParam.getParentAuthId())
				.iconClass(updateParam.getIconClass())
				.hasChild(updateParam.getHasChild())
				.id(updateParam.getId())
				.build());
	}

	/**
	 * 删除权限
	 *
	 * @param deleteParam 权限删除参数
	 */
	public void deleteAuth(DeleteParam deleteParam) {
		Integer authId = deleteParam.getId();
		// 父子权限判断
		Auth auth = authMapper.findAuthById(authId);
		if (auth == null) {
			throw new AppRuntimeException("被删除的权限不存在");
		}
		Integer parentAuthId = auth.getParentAuthId();
		// 子权限
		if (parentAuthId != null && parentAuthId != 0) {
			authMapper.deleteAuth(authId);
			// 删除完成后判断当前子权限所属的父权限中的子权限是否为空，如果为空，则修改父权限的has_child字段为0
			// 根据子权限ID查询父权限ID
			int size = authMapper.findChildAuthByParentAuthId(parentAuthId).size();
			if (size == 0) {
				authMapper.updateAuth(Auth.builder()
						.id(parentAuthId)
						.hasChild(0)
						.build());
			}

		} else {
			// 父权限
			// 判断父权限是否拥有子权限（根据hasChild来判断），如果有，则拒绝删除
			if (auth.getHasChild() == 1) {
				throw new AppRuntimeException("无法删除有子权限的父权限");
			}
			authMapper.deleteAuth(authId);
		}
	}
}

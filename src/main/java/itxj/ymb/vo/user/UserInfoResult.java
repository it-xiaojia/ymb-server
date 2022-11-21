package itxj.ymb.vo.user;

import itxj.ymb.pojo.User;
import itxj.ymb.vo.role.RoleInfoResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息结果，不对前端展示账号和密码
 */
@Getter
@Setter
@ToString
public class UserInfoResult extends User implements Serializable {
	/**
	 * 用户角色
	 */
	private RoleInfoResult userRole;
	/**
	 * 菜单列表
	 */
	private List<AuthMenu> authMenuList;

	public UserInfoResult(User user) {
		super(user.getId(), user.getAccount(), user.getPassword(), user.getUsername(), user.getStatusCode(), null, user.getLastLoginTime());
	}
}

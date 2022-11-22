package itxj.ymb.vo.user;

import itxj.ymb.pojo.User;
import itxj.ymb.vo.auth.AuthVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息结果
 */
@Getter
@Setter
@ToString
public class UserVO implements Serializable {
	private User user;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 一级权限菜单列表
	 */
	private List<AuthVO> level1AuthList;
	/**
	 * 二级权限菜单列表
	 */
	private List<AuthVO> level2AuthList;

	public UserVO(User user) {
		// 对账号密码进行空值脱敏处理
		user.setAccount(null);
		user.setPassword(null);
		this.user = user;
	}
}

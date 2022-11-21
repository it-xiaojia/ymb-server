package itxj.ymb.vo.user;

import itxj.ymb.pojo.User;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户信息结果
 */
@Getter
@ToString
public class UserVO implements Serializable {
	private User user;

	// TODO 用户登录角色查询与角色权限待实现
	public void setUser(User user) {
		// 对账号密码进行空值脱敏处理
		user.setAccount(null);
		user.setPassword(null);
		this.user = user;
	}
}

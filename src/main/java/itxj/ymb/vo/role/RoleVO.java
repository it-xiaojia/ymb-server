package itxj.ymb.vo.role;

import itxj.ymb.pojo.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色信息结果
 */
@Getter
@Setter
@ToString
public class RoleVO implements Serializable {
	private Role role;

	public RoleVO(Role role) {
		this.role = role;
	}
}

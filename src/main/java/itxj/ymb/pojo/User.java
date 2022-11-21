package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * 用户
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User implements Serializable {
	/**
	 * 用户ID
	 */
	private Integer id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户账号状态字典
	 */
	private Integer statusCode;
	/**
	 * 用户角色
	 */
	private Role role;
	/**
	 * 上次成功登录的时间
	 */
	private String lastLoginTime;
}

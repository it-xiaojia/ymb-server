package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ymb_user")
public class User implements Serializable {
	/**
	 * 用户ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 账号
	 */
	@TableField("val_account")
	private String account;
	/**
	 * 密码
	 */
	@TableField("val_password")
	private String password;
	/**
	 * 用户名
	 */
	@TableField("val_username")
	private String username;
	/**
	 * 用户账号状态
	 */
	@TableField("account_status_code")
	private Integer accountStatusCode;
	/**
	 * 用户角色ID
	 */
	@TableField("user_role_id")
	private Integer userRoleId;
	/**
	 * 上次成功登录的时间
	 */
	@TableField("val_last_login_time")
	private String lastLoginTime;
}

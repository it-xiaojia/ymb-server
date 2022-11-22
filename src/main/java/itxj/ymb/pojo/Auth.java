package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 权限
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@TableName("ymb_auth")
public class Auth implements Serializable {
	/**
	 * 权限ID
	 */
	@TableId(value = "auth_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 权限名称
	 */
	@TableField("val_name")
	private String name;
	/**
	 * 权限url，对应的前端组件路由名称
	 */
	@TableField("val_url")
	private String url;
	/**
	 * 父权限ID，用于前端区分菜单级别，为空是一级菜单
	 */
	@TableField("parent_auth_id")
	private Integer parentAuthId;
	/**
	 * 图标类
	 */
	@TableField("val_icon_class")
	private String iconClass;
}

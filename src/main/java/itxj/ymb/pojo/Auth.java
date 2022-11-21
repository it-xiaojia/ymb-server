package itxj.ymb.pojo;

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
public class Auth implements Serializable {
	/**
	 * 权限ID
	 */
	private Integer id;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限url，对应的前端组件路由名称
	 */
	private String url;
	/**
	 * 父权限ID，用于前端区分菜单级别，为空是一级菜单
	 */
	private Integer parentAuthId;
	/**
	 * 图标类
	 */
	private String iconClass;
	/**
	 * 是否有子权限：0无，1有
	 */
	private Integer hasChild;
}

package itxj.ymb.vo.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 权限菜单项
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthMenu implements Serializable {
	/**
	 * 菜单唯一标识
	 */
	private Integer key;
	/**
	 * 菜单标题
	 */
	private String title;
	/**
	 * 父级菜单ID
	 */
	private Integer parentId;
	/**
	 * 图标类型（以ant-design-vue中的图标为准）
	 */
	private String iconType;
	/**
	 * 菜单路由地址
	 */
	private String url;
	/**
	 * 子菜单列表
	 */
	private List<AuthMenu> children;
}

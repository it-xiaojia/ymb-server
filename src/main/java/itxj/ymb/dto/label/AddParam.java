package itxj.ymb.dto.label;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限新增参数
 */
@Data
public class AddParam implements Serializable {
	/**
	 * 权限名称
	 */
	@NotNull(message = "权限名称不能为空")
	@Length(max = 16, message = "权限名称不能超过16个字符")
	private String name;
	/**
	 * 权限url，对应的前端组件路由名称
	 */
	@Length(max = 255, message = "权限url不能超过255个字符")
	private String url;
	/**
	 * 父权限ID，用于前端区分菜单级别，为空是一级菜单
	 */
	private Integer parentAuthId;
	/**
	 * 图标类
	 */
	@NotNull(message = "图标类不能为空")
	@Length(max = 32, message = "图标类不能超过32个字符")
	private String iconClass;
}

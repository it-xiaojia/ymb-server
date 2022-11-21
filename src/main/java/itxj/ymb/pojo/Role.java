package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Role implements Serializable {
	/**
	 * 角色ID
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色所拥有的权限
	 */
	private List<Auth> authList;
}

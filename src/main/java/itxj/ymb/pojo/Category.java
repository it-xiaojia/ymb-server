package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * 文章分类
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category implements Serializable {
	/**
	 * 分类ID
	 */
	private Integer id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分类所属作者
	 */
	private User author;
	/**
	 * 父分类ID
	 */
	private Integer parentCategoryId;
}

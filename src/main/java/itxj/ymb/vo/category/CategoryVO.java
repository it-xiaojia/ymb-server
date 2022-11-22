package itxj.ymb.vo.category;

import itxj.ymb.pojo.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文章分类信息结果
 */
@Getter
@Setter
@ToString
public class CategoryVO implements Serializable {
	private Category category;

	public CategoryVO(Category category) {
		this.category = category;
	}
}

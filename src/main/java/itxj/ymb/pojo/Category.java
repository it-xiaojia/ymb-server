package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ymb_category")
public class Category implements Serializable {
	/**
	 * 分类ID
	 */
	@TableId(value = "category_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 分类名称
	 */
	@TableField("val_name")
	private String name;
	/**
	 * 创建分类的用户ID
	 */
	@TableField("create_user_id")
	private Integer createUserId;
	/**
	 * 父分类ID
	 */
	@TableField("parent_category_id")
	private Integer parentCategoryId;
}

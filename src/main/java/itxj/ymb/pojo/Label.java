package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import java.io.Serializable;

/**
 * 文章标签
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@TableName("ymb_label")
public class Label implements Serializable {
	/**
	 * 标签ID
	 */
	@TableId(value = "label_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 标签名称
	 */
	@TableField("val_name")
	private String name;
}

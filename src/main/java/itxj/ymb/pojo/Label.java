package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 角色
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@TableName("ymb_role")
public class Role implements Serializable {
	/**
	 * 角色ID
	 */
	@TableId(value = "role_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色名称
	 */
	@TableField("val_name")
	private String name;
}

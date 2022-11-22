package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 日志
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@TableName("ymb_log")
public class Log implements Serializable {
	/**
	 * 日志ID
	 */
	@TableId(value = "log_id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 日志名称
	 */
	@TableField("val_name")
	private String name;
	/**
	 * 日志内容
	 */
	@TableField("val_section")
	private String section;
	/**
	 * 日志记录时间
	 */
	@TableField("val_markup_date")
	private String markupDate;
	/**
	 * 日志级别代码
	 */
	@TableField("log_level_code")
	private Integer logLevelCode;
}

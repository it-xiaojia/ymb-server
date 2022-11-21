package itxj.ymb.pojo;

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
public class Log implements Serializable {
	/**
	 * 日志ID
	 */
	private Integer id;
	/**
	 * 日志名称
	 */
	private String name;
	/**
	 * 日志内容
	 */
	private String section;
	/**
	 * 日志记录时间
	 */
	private String markupDate;
	/**
	 * 日志级别
	 */
	private Integer level;
}

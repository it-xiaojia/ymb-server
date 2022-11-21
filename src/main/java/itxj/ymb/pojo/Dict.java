package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * 字典
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Dict implements Serializable {
	/**
	 * 字典ID
	 */
	private Integer id;
	/**
	 * 字典键
	 */
	private String key;
	/**
	 * 字典值
	 */
	private String value;
	/**
	 * 字典所属分类
	 */
	private DictCategory dictCategory;
}

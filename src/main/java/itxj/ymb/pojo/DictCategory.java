package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * 字典分类
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DictCategory implements Serializable {
	/**
	 * 字典分类ID
	 */
	private Integer id;
	/**
	 * 字典分类名称
	 */
	private String name;
}

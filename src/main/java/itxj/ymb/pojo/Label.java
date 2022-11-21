package itxj.ymb.pojo;

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
public class Label implements Serializable {
	/**
	 * 标签ID
	 */
	private Integer id;
	/**
	 * 标签名称
	 */
	private String name;
}

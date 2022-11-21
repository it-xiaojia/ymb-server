package itxj.ymb.vo.system;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LastInsertIdResult implements Serializable {
	/**
	 * 上一次插入成功的ID
	 */
	private Integer lastInsertId;
}

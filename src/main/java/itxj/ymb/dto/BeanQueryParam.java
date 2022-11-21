package itxj.ymb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 对象查询条件
 */
@Getter
@Setter
@ToString
public class BeanQueryParam implements Serializable {
	@NotNull(message = "ID不能为空")
	@Max(value = 999999, message = "ID最大值不能超过999999")
	private Integer id;
}

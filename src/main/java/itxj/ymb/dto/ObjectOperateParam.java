package itxj.ymb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通用对象操作参数：单个对象的查询与删除
 */
@Getter
@Setter
@ToString
public class ObjectOperateParam implements Serializable {
	@NotNull(message = "ID不能为空")
	@Min(value = 1, message = "ID不能小于等于0")
	@Max(value = 999999, message = "ID最大值不能超过999999")
	private Integer id;
}

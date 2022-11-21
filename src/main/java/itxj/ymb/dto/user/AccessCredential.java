package itxj.ymb.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 访问特定接口需要的凭证
 */
@Setter
@Getter
@ToString
public class AccessCredential implements Serializable {
	/**
	 * 无token时的访问凭证
	 */
	@NotNull(message = "访问凭证不能为空")
	@Length(min = 1, message = "访问凭证不能为空")
	private String credential;
}

package itxj.ymb.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 密码参数
 */
@Getter
@Setter
@ToString
public class PasswordParam implements Serializable {
    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @Min(value = 1, message = "用户ID不能小于1")
    private Integer userId;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Length(max = 16, message = "密码长度不能超过16位")
    private String password;
}

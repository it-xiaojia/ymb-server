package itxj.ymb.dto.user;

import itxj.ymb.util.DataUtil;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录参数
 */
@Getter
@ToString
public class LoginParam implements Serializable {
    /**
     * 账号
     */
    @NotNull(message = "账号不能为空")
    @Length(min = 1, message = "账号不能为空")
    private String account;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Length(min = 1, message = "密码不能为空")
    private String password;

    public void setAccount(String account) {
        this.account = DataUtil.isStringNotNull(account) ? DataUtil.stringToMd5(account) : account;
    }

    public void setPassword(String password) {
        this.password = DataUtil.isStringNotNull(password) ? DataUtil.stringToMd5(password) : password;
    }
}

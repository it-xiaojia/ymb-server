package itxj.ymb.dto.auth;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限列表查询参数
 */
@Getter
@Setter
@ToString
@Builder
public class AuthListQueryParam implements Serializable {
    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    @Min(value = 1, message = "角色ID不能小于等于0")
    @Max(value = 999999, message = "角色ID不能超过999999")
    private Integer roleId;
    /**
     * 父权限ID
     */
    @Max(value = 999999, message = "父权限ID不能超过999999")
    private Integer parentAuthId;
}

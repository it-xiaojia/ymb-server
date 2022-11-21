package itxj.ymb.dto.auth;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限删除参数
 */
@Data
public class DeleteParam implements Serializable {
    /**
     * 权限ID
     */
    @NotNull(message = "权限ID不能为空")
    @Min(value = 1, message = "权限ID不能小于1")
    private Integer id;
}

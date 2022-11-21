package itxj.ymb.dto.user;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * 查询参数
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QueryParam {
    /**
     * 用户ID，如果不根据此项查询，将此项置为-1且isCurrentUser=true
     */
    @NotNull(message = "用户ID不能为空")
    private Integer id;
}

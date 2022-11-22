package itxj.ymb.dto.auth;

import itxj.ymb.dto.PageQueryParam;
import lombok.*;

import java.io.Serializable;

/**
 * 查询参数
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthPageQueryParam extends PageQueryParam implements Serializable {
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 父权限ID
     */
    private Integer parentAuthId;
}

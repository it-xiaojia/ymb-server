package itxj.ymb.dto.auth;

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
public class ListQueryParam implements Serializable {
    /**
     * 角色ID，为0或者为空则查询所有权限列表
     */
    private Integer roleId;
}

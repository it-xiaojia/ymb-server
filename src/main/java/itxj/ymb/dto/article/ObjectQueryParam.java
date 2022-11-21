package itxj.ymb.dto.article;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 文章对象查询参数
 */
@Data
public class ObjectQueryParam {
    /**
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空")
    @Min(value = 1, message = "文章ID不能小于1")
    private Integer id;
}

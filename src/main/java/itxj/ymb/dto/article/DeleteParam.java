package itxj.ymb.dto.article;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章删除参数
 */
@Data
public class DeleteParam implements Serializable {
    /**
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空")
    @Min(value = 1, message = "文章ID不能小于1")
    private Integer articleId;
    /**
     * 作者ID
     */
    @NotNull(message = "作者ID不能为空")
    @Min(value = 1, message = "作者ID不能小于1")
    private Integer authorId;
}

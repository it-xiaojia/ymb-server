package itxj.ymb.dto.article;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章修改参数
 */
@Data
public class UpdateParam implements Serializable {
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
    /**
     * 标题
     */
    @NotNull(message = "标题不能为空")
    @Length(max = 64, message = "标题长度不能超过64个字符")
    private String title;
    /**
     * 文章内容
     */
    @NotNull(message = "文章内容不能为空")
    private String section;
}

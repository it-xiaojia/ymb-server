package itxj.ymb.dto.article;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章书写参数
 */
@Data
public class WriteParam implements Serializable {
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

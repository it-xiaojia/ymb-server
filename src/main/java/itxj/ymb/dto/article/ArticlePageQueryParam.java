package itxj.ymb.dto.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.pojo.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 文章分页查询参数
 */
@Getter
@Setter
@ToString
public class ArticlePageQueryParam extends Page<Article> implements Serializable {
    /**
     * 标题
     */
    @Length(max = 64, message = "标题查询字数不能大于64")
    private String title;
    /**
     * 文章内容
     */
    @Length(max = 255, message = "文章查询字数不能大于255")
    private String section;
}

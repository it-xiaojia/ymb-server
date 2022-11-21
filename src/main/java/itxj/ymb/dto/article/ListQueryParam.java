package itxj.ymb.dto.article;

import itxj.ymb.dto.PageQueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 文章列表查询参数
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListQueryParam extends PageQueryParam implements Serializable {
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

    @Override
    public String toString() {
        return "QueryParam{" +
                "currentPage='" + super.getCurrentPage() + '\'' +
                ", pageSize='" + super.getPageSize() + '\'' +
                ", queryString='" + super.getQueryString() + '\'' +
                ", title='" + title + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}

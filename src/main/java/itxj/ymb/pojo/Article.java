package itxj.ymb.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 文章
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Article implements Serializable {
    /**
     * 文章ID
     */
    private Integer id;
    /**
     * 作者
     */
    private User author;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布日期
     */
    private String publishDate;
    /**
     * 更新日期
     */
    private String updateDate;
    /**
     * 文章内容
     */
    private String section;
    /**
     * 文章所属分类
     */
    private Category category;
    /**
     * 文章标签列表
     */
    private List<Label> labelList;
    /**
     * 文章状态
     */
    private Integer statusCode;

    public void setAuthor(User author) {
        // 不对外暴露作者相关敏感信息
        author.setRole(null);
        author.setAccount(null);
        author.setPassword(null);
        author.setLastLoginTime(null);
        this.author = author;
    }
}

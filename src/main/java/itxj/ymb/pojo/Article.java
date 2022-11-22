package itxj.ymb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * 文章
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@TableName("ymb_article")
public class Article implements Serializable {
    /**
     * 文章ID
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer id;
    /**
     * 作者ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 标题
     */
    @TableField("val_title")
    private String title;
    /**
     * 发布日期
     */
    @TableField("val_publish_date")
    private String publishDate;
    /**
     * 更新日期
     */
    @TableField("val_update_date")
    private String updateDate;
    /**
     * 文章内容
     */
    @TableField("val_section")
    private String section;
    /**
     * 文章所属分类ID
     */
    @TableField("category_id")
    private Category categoryId;
    /**
     * 文章状态代码
     */
    @TableField("article_status_code")
    private Integer articleStatusCode;
}

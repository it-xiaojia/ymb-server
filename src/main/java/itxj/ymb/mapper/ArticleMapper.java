package itxj.ymb.mapper;

import itxj.ymb.pojo.Article;
import itxj.ymb.dto.article.ListQueryParam;
import com.github.pagehelper.Page;

/**
 * 文章数据接口
 */
public interface ArticleMapper {
    /**
     * 通过文章ID查询文章
     *
     * @param id 文章ID
     * @return 返回文章对象
     */
    Article findArticleById(Integer id);

    /**
     * 查询文章列表
     *
     * @param queryParam 查询参数
     * @return 如果查询参数为空，返回全部文章列表，否则返回符合条件的文章列表
     */
    Page<Article> findArticleList(ListQueryParam queryParam);

    /**
     * 新增一篇文章
     *
     * @param article 文章对象
     */
    void addArticle(Article article);

    /**
     * 通过文章对象删除一篇文章
     *
     * @param article 文章对象
     */
    void deleteArticle(Article article);

    /**
     * 更新文章数据
     *
     * @param article 文章对象
     */
    void updateArticle(Article article);
}

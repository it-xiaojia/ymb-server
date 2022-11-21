package itxj.ymb.vo.article;

import itxj.ymb.pojo.Article;

import java.io.Serializable;

/**
 * 文章信息结果
 */
public class ArticleInfoResult extends Article implements Serializable {
	public ArticleInfoResult(Article article) {
		super(article.getId(), article.getAuthor(), article.getTitle(), article.getPublishDate(), article.getUpdateDate(), article.getSection(), article.getCategory(), article.getLabelList(), article.getStatusCode());
	}
}

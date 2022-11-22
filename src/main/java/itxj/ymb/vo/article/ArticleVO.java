package itxj.ymb.vo.article;

import itxj.ymb.pojo.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 文章信息结果
 */
@Getter
@Setter
@ToString
public class ArticleVO implements Serializable {
	private Article article;

	public ArticleVO(Article article) {
		this.article = article;
	}
}

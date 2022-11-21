package itxj.ymb.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.util.RedisManager;
import itxj.ymb.dto.article.*;
import itxj.ymb.exception.AppRuntimeException;
import itxj.ymb.mapper.ArticleMapper;
import itxj.ymb.mapper.UserMapper;
import itxj.ymb.pojo.Article;
import itxj.ymb.pojo.User;
import itxj.ymb.util.DataUtil;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.article.ArticleInfoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 文章业务
 */
@Service
@Transactional
public class ArticleService {
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private RedisManager redisManager;

	/**
	 * 获取文章列表（不获取文章内容）
	 *
	 * @param queryParam 查询参数
	 * @return 如果查询参数为空，查询所有的文章列表，否则根据指定条件查询文章
	 */
	public PageResult getArticleList(ListQueryParam queryParam) {
		Integer currentPage = queryParam.getCurrentPage();
		Integer pageSize = queryParam.getPageSize();
		PageHelper.startPage(currentPage, pageSize);
		Page<Article> articlePage = articleMapper.findArticleList(queryParam);
		Page<ArticleInfoResult> targetPage = new Page<>();
		for (Article article : articlePage) {
			targetPage.add((ArticleInfoResult) article);
		}
		return new PageResult(articlePage.getTotal(), targetPage.getResult());
	}

	/**
	 * 查询一篇文章
	 *
	 * @param queryParam 查询参数
	 * @return 返回文章对象
	 */
	public ArticleInfoResult getArticle(BeanQueryParam queryParam) {
		Article article = articleMapper.findArticleById(queryParam.getId());
		if (article == null) {
			throw new AppRuntimeException("无此文章的数据");
		}
		return (ArticleInfoResult) article;
	}

	/**
	 * 写文章
	 *
	 * @param writeParam 写文章需要的参数
	 */
	public void writeArticle(WriteParam writeParam, TokenVO tokenVO) {
		User user = DataUtil.getUserInfoByToken(userMapper, redisManager, tokenVO.getAccessToken());
		user.setAccount(null);
		user.setPassword(null);
		articleMapper.addArticle(Article.builder()
				.author(user)
				.title(writeParam.getTitle())
				// 写文章时，文章更新日期和文章发布日期为当前系统的时间
				.publishDate(DataUtil.convertCurrentTimeToString())
				.updateDate(DataUtil.convertCurrentTimeToString())
				.section(writeParam.getSection())
				.build());
	}

	/**
	 * 修改文章
	 *
	 * @param updateParam 修改文章需要的参数
	 */
	public void updateArticle(UpdateParam updateParam) {
		articleMapper.updateArticle(Article.builder()
				.id(updateParam.getArticleId())
				.author(User.builder().id(updateParam.getAuthorId()).build())
				.title(updateParam.getTitle())
				// 改文章时，文章发布日期不变，文章更新日期为当前系统的时间
				.updateDate(DataUtil.convertCurrentTimeToString())
				.section(updateParam.getSection())
				.build());
	}

	/**
	 * 删除文章
	 *
	 * @param deleteParam 删除文章需要的参数
	 */
	public void deleteArticle(DeleteParam deleteParam) {
		articleMapper.deleteArticle(Article.builder()
				.id(deleteParam.getArticleId())
				.author(User.builder().id(deleteParam.getAuthorId()).build())
				.build());
	}
}

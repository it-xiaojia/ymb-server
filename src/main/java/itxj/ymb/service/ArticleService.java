package itxj.ymb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.article.ArticleAddParam;
import itxj.ymb.dto.article.ArticlePageQueryParam;
import itxj.ymb.dto.article.ArticleUpdateParam;
import itxj.ymb.mapper.ArticleMapper;
import itxj.ymb.pojo.Article;
import itxj.ymb.vo.article.ArticleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章业务
 */
@Service
@Transactional
public class ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	public ArticleVO queryObject(ObjectOperateParam queryParam) {
		return new ArticleVO(articleMapper.selectById(queryParam.getId()));
	}

	public Page<ArticleVO> queryPage(ArticlePageQueryParam queryParam) {
		Page<ArticleVO> articleVOPage = new Page<>();

		QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
		String title = queryParam.getTitle();
		String section = queryParam.getSection();
		if (!StringUtils.isEmpty(title)) {
			articleQueryWrapper.like("val_title", section);
		}
		if (!StringUtils.isEmpty(section)) {
			articleQueryWrapper.like("val_section", section);
		}

		Page<Article> articlePage = articleMapper.selectPage(queryParam, articleQueryWrapper);

		articleVOPage.setSize(articlePage.getSize());
		articleVOPage.setCurrent(articlePage.getCurrent());

		List<ArticleVO> articleVOList = new ArrayList<>();
		List<Article> records = articlePage.getRecords();

		for (Article record : records) {
			articleVOList.add(new ArticleVO(record));
		}

		articleVOPage.setRecords(articleVOList);
		return articleVOPage;
	}

	public void add(ArticleAddParam addParam) {

	}

	public void update(ArticleUpdateParam updateParam) {

	}

	public void delete(ObjectOperateParam deleteParam) {

	}
}

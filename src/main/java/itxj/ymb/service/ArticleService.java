package itxj.ymb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.article.ArticleAddParam;
import itxj.ymb.dto.article.ArticlePageQueryParam;
import itxj.ymb.dto.article.ArticleUpdateParam;
import itxj.ymb.mapper.ArticleMapper;
import itxj.ymb.vo.article.ArticleVO;
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

	public ArticleVO queryObject(ObjectOperateParam queryParam) {
		return null;
	}

	public Page<ArticleVO> queryPage(ArticlePageQueryParam queryParam) {
		return null;
	}

	public void add(ArticleAddParam addParam) {

	}

	public void update(ArticleUpdateParam updateParam) {

	}

	public void delete(ObjectOperateParam deleteParam) {

	}
}

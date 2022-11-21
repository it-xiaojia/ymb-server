package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.article.AddParam;
import itxj.ymb.dto.article.UpdateParam;
import itxj.ymb.mapper.ArticleMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.article.ArticleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章业务
 */
@Service
@Transactional
public class ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	public ArticleVO queryObject(ObjectQueryParam queryParam) {
		return null;
	}

	public List<PageResult> queryList(itxj.ymb.dto.article.ListQueryParam queryParam) {
		return null;
	}

	public void add(AddParam addParam) {

	}

	public void update(UpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

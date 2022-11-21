package itxj.ymb.service;

import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.category.AddParam;
import itxj.ymb.dto.category.UpdateParam;
import itxj.ymb.mapper.CategoryMapper;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.category.CategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章分类业务
 */
@Service
@Transactional
public class CategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	public CategoryVO queryObject(ObjectQueryParam queryParam) {
		return null;
	}

	public List<PageResult> queryList(itxj.ymb.dto.category.ListQueryParam queryParam) {
		return null;
	}

	public void add(AddParam addParam) {

	}

	public void update(UpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

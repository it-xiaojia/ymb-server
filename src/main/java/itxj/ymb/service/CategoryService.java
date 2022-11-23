package itxj.ymb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.category.CategoryAddParam;
import itxj.ymb.dto.category.CategoryPageQueryParam;
import itxj.ymb.dto.category.CategoryUpdateParam;
import itxj.ymb.mapper.CategoryMapper;
import itxj.ymb.vo.category.CategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 文章分类业务
 */
@Service
@Transactional
public class CategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	public CategoryVO queryObject(ObjectOperateParam queryParam) {
		return null;
	}

	public Page<CategoryVO> queryPage(CategoryPageQueryParam queryParam) {
		return null;
	}

	public void add(CategoryAddParam addParam) {

	}

	public void update(CategoryUpdateParam updateParam) {

	}

	public void delete(DeleteParam deleteParam) {

	}
}

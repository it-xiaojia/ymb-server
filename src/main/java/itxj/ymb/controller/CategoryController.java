package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.category.CategoryAddParam;
import itxj.ymb.dto.category.CategoryPageQueryParam;
import itxj.ymb.dto.category.CategoryUpdateParam;
import itxj.ymb.service.CategoryService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.category.CategoryVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("category")
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<CategoryVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		CategoryVO categoryInfoResult = categoryService.queryObject(queryParam);
		return new Result<CategoryVO>().generateSuccessResponseEntity(categoryInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated CategoryPageQueryParam queryParam) {
		List<PageResult> categoryInfoResultList = categoryService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(categoryInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated CategoryAddParam addParam) {
		categoryService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated CategoryUpdateParam updateParam) {
		categoryService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		categoryService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

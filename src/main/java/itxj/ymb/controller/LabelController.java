package itxj.ymb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.label.LabelAddParam;
import itxj.ymb.dto.label.LabelPageQueryParam;
import itxj.ymb.dto.label.LabelUpdateParam;
import itxj.ymb.service.LabelService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.label.LabelVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("label")
public class LabelController {
	@Resource
	private LabelService labelService;

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<LabelVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		LabelVO labelInfoResult = labelService.queryObject(queryParam);
		return new Result<LabelVO>().generateSuccessResponseEntity(labelInfoResult);
	}

	@PostMapping("queryPage")
	@ApiLog
	public ResponseEntity<Page<LabelVO>> queryList(@RequestBody @Validated LabelPageQueryParam queryParam) {
		Page<LabelVO> labelVOPage = labelService.queryPage(queryParam);
		return new Result<Page<LabelVO>>().generateSuccessResponseEntity(labelVOPage);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated LabelAddParam addParam) {
		labelService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated LabelUpdateParam updateParam) {
		labelService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		labelService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

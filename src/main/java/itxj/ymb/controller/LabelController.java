package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.label.AddParam;
import itxj.ymb.dto.label.ListQueryParam;
import itxj.ymb.dto.label.UpdateParam;
import itxj.ymb.service.LabelService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.label.LabelVO;
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
@RequestMapping("label")
public class LabelController {
	@Resource
	private LabelService labelService;

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<LabelVO> queryObject(@RequestBody @Validated ObjectQueryParam queryParam) {
		LabelVO labelInfoResult = labelService.queryObject(queryParam);
		return new Result<LabelVO>().generateSuccessResponseEntity(labelInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated ListQueryParam queryParam) {
		List<PageResult> labelInfoResultList = labelService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(labelInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated AddParam addParam) {
		labelService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated UpdateParam updateParam) {
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

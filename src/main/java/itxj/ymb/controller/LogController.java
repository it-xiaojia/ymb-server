package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.log.LogAddParam;
import itxj.ymb.dto.log.LogPageQueryParam;
import itxj.ymb.dto.log.LogUpdateParam;
import itxj.ymb.service.LogService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.log.LogVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志控制器
 */
@RestController
@RequestMapping("log")
public class LogController {
	@Resource
	private LogService logService;

	@PostMapping("queryObject")
	@ApiLog
	public ResponseEntity<LogVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
		LogVO logInfoResult = logService.queryObject(queryParam);
		return new Result<LogVO>().generateSuccessResponseEntity(logInfoResult);
	}

	@PostMapping("queryList")
	@ApiLog
	public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated LogPageQueryParam queryParam) {
		List<PageResult> logInfoResultList = logService.queryList(queryParam);
		return new Result<List<PageResult>>().generateSuccessResponseEntity(logInfoResultList);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated LogAddParam addParam) {
		logService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("update")
	@ApiLog
	public ResponseEntity<?> update(@RequestBody @Validated LogUpdateParam updateParam) {
		logService.update(updateParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("delete")
	@ApiLog
	public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
		logService.delete(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

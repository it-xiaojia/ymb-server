package itxj.ymb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.log.LogAddParam;
import itxj.ymb.dto.log.LogPageQueryParam;
import itxj.ymb.service.LogService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.log.LogVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

	@PostMapping("queryPage")
	@ApiLog
	public ResponseEntity<Page<LogVO>> queryPage(@RequestBody @Validated LogPageQueryParam queryParam) {
		Page<LogVO> logVOPage = logService.queryPage(queryParam);
		return new Result<Page<LogVO>>().generateSuccessResponseEntity(logVOPage);
	}

	@PostMapping("add")
	@ApiLog
	public ResponseEntity<?> add(@RequestBody @Validated LogAddParam addParam) {
		logService.add(addParam);
		return new Result<>().generateSuccessResponseEntity();
	}

	@PostMapping("clear")
	@ApiLog
	public ResponseEntity<?> clear(@RequestBody @Validated DeleteParam deleteParam) {
		logService.clear(deleteParam);
		return new Result<>().generateSuccessResponseEntity();
	}
}

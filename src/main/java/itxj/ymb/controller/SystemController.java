package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.service.SystemService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.system.LastInsertIdResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统控制器
 */
@RestController
@RequestMapping("system")
public class SystemController {
    @Resource
    private SystemService systemService;

    @PostMapping("getLastInsertId")
    @ApiLog
    public ResponseEntity<LastInsertIdResult> getLastInsertId() {
        return new Result<LastInsertIdResult>().generateSuccessResponseEntity("获取插入成功的数据ID成功", systemService.getLastInsertId());
    }
}

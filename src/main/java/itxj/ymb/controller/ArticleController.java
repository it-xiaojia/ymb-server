package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.DeleteParam;
import itxj.ymb.dto.ObjectQueryParam;
import itxj.ymb.dto.article.AddParam;
import itxj.ymb.dto.article.ListQueryParam;
import itxj.ymb.dto.article.UpdateParam;
import itxj.ymb.service.ArticleService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.article.ArticleVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章控制器
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("queryObject")
    @ApiLog
    public ResponseEntity<ArticleVO> queryObject(@RequestBody @Validated ObjectQueryParam queryParam) {
        ArticleVO articleInfoResult = articleService.queryObject(queryParam);
        return new Result<ArticleVO>().generateSuccessResponseEntity(articleInfoResult);
    }

    @PostMapping("queryList")
    @ApiLog
    public ResponseEntity<List<PageResult>> queryList(@RequestBody @Validated ListQueryParam queryParam) {
        List<PageResult> articleInfoResultList = articleService.queryList(queryParam);
        return new Result<List<PageResult>>().generateSuccessResponseEntity(articleInfoResultList);
    }

    @PostMapping("add")
    @ApiLog
    public ResponseEntity<?> add(@RequestBody @Validated AddParam addParam) {
        articleService.add(addParam);
        return new Result<>().generateSuccessResponseEntity();
    }

    @PostMapping("update")
    @ApiLog
    public ResponseEntity<?> update(@RequestBody @Validated UpdateParam updateParam) {
        articleService.update(updateParam);
        return new Result<>().generateSuccessResponseEntity();
    }

    @PostMapping("delete")
    @ApiLog
    public ResponseEntity<?> delete(@RequestBody @Validated DeleteParam deleteParam) {
        articleService.delete(deleteParam);
        return new Result<>().generateSuccessResponseEntity();
    }
}

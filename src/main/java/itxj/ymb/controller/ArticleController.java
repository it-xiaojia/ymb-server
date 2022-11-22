package itxj.ymb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import itxj.ymb.annotation.ApiLog;
import itxj.ymb.dto.ObjectOperateParam;
import itxj.ymb.dto.article.ArticleAddParam;
import itxj.ymb.dto.article.ArticlePageQueryParam;
import itxj.ymb.dto.article.ArticleUpdateParam;
import itxj.ymb.service.ArticleService;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.article.ArticleVO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public ResponseEntity<ArticleVO> queryObject(@RequestBody @Validated ObjectOperateParam queryParam) {
        ArticleVO articleInfoResult = articleService.queryObject(queryParam);
        return new Result<ArticleVO>().generateSuccessResponseEntity(articleInfoResult);
    }

    @PostMapping("queryPage")
    @ApiLog
    public ResponseEntity<Page<ArticleVO>> queryPage(@RequestBody @Validated ArticlePageQueryParam queryParam) {
        Page<ArticleVO> articleInfoResultList = articleService.queryPage(queryParam);
        return new Result<Page<ArticleVO>>().generateSuccessResponseEntity(articleInfoResultList);
    }

    @PostMapping("add")
    @ApiLog
    public ResponseEntity<?> add(@RequestBody @Validated ArticleAddParam addParam) {
        articleService.add(addParam);
        return new Result<>().generateSuccessResponseEntity();
    }

    @PostMapping("update")
    @ApiLog
    public ResponseEntity<?> update(@RequestBody @Validated ArticleUpdateParam updateParam) {
        articleService.update(updateParam);
        return new Result<>().generateSuccessResponseEntity();
    }

    @PostMapping("delete")
    @ApiLog
    public ResponseEntity<?> delete(@RequestBody @Validated ObjectOperateParam deleteParam) {
        articleService.delete(deleteParam);
        return new Result<>().generateSuccessResponseEntity();
    }
}

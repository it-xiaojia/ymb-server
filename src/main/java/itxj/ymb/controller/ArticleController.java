package itxj.ymb.controller;

import itxj.ymb.annotation.ApiLog;
import itxj.ymb.annotation.NoAuth;
import itxj.ymb.dto.BeanQueryParam;
import itxj.ymb.dto.article.DeleteParam;
import itxj.ymb.dto.article.ListQueryParam;
import itxj.ymb.dto.article.UpdateParam;
import itxj.ymb.dto.article.WriteParam;
import itxj.ymb.service.ArticleService;
import itxj.ymb.vo.PageResult;
import itxj.ymb.vo.Result;
import itxj.ymb.vo.TokenVO;
import itxj.ymb.vo.article.ArticleInfoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文章控制器
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private HttpServletRequest request;

    @PostMapping("getArticleList")
    @ApiLog
    @NoAuth
    public ResponseEntity<PageResult> getArticleList(@RequestBody @Validated ListQueryParam queryParam) {
        return new Result<PageResult>().generateSuccessResponseEntity("文章列表查询成功", articleService.getArticleList(queryParam));
    }

    @PostMapping("getArticle")
    @ApiLog
    @NoAuth
    public ResponseEntity<ArticleInfoResult> getArticleByArticleId(@RequestBody @Validated BeanQueryParam queryParam) {
        return new Result<ArticleInfoResult>().generateSuccessResponseEntity("文章查询成功", articleService.getArticle(queryParam));
    }

    @PostMapping("writeArticle")
    @ApiLog
    public ResponseEntity<?> writeArticle(@RequestBody @Validated WriteParam writeParam) {
        articleService.writeArticle(writeParam, new TokenVO(request));
        return new Result<>().generateSuccessResponseEntity("成功写入一篇文章");
    }

    @PostMapping("updateArticle")
    @ApiLog
    public ResponseEntity<?> updateArticle(@RequestBody @Validated UpdateParam updateParam) {
        articleService.updateArticle(updateParam);
        return new Result<>().generateSuccessResponseEntity("成功修改一篇文章");
    }

    @PostMapping("deleteArticle")
    @ApiLog
    public ResponseEntity<?> deleteArticle(@RequestBody @Validated DeleteParam deleteParam) {
        articleService.deleteArticle(deleteParam);
        return new Result<>().generateSuccessResponseEntity("成功删除一篇文章");
    }
}

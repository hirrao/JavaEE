package com.hirrao.javaee.controller;

import com.hirrao.javaee.entity.Article;
import com.hirrao.javaee.entity.Result;
import com.hirrao.javaee.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @PostMapping("/add")
    public Result addArticle(@RequestBody Map<String, String> map) {
        String title = map.get("title");
        String description = map.get("description");
        String image = map.get("image");
        String content = map.get("content");
        articleService.addArticle(title, description, image, content);
        return Result.success();
    }


    @PostMapping("/articlesInfo")
    public Result articlesInfo(@RequestBody Map<String, String> map){
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        return Result.success(articleService.articlesInfo(curPage, size));
    }

    @PostMapping("/modifyArticleInfo")
    public Result modifyArticleInfo(@RequestBody Map<String, String> map) {
        var id = Long.parseLong(map.get("id"));
        var title = map.get("title");
        var description = map.get("description");
        var image = map.get("image");
        var content = map.get("content");

        logger.debug("modifyArticleInfo id:{}", id);
        logger.debug("modifyArticleInfo title:{}", title);
        logger.debug("modifyArticleInfo description:{}", description);
        logger.debug("modifyArticleInfo image:{}", image);
        logger.debug("modifyArticleInfo content:{}", content);

        articleService.modifyArticleInfo(id, title, description, image, content);
        return Result.success();
    }

    @PostMapping("/deleteArticle")
    public Result deleteArticle(@RequestBody Map<String, String> map){
        var id = Long.parseLong(map.get("id"));
        articleService.deleteArticle(id);
        logger.debug("delete id:" + id);
        return Result.success();
    }

    @PostMapping("/searchArticleByCondition")
    public Result searchArticleByCondition(@RequestBody Map<String, String> map){
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var searchCondition = map.get("searchCondition");
        var conditionValue = map.get("conditionValue");

        logger.debug("curPage:{}", curPage);
        logger.debug("size:{}", size);
        logger.debug("searchCondition:{}", searchCondition);
        logger.debug("conditionValue:{}", conditionValue);

        if(!searchCondition.isEmpty() && !conditionValue.isEmpty()){
//            return Result.success();
            return Result.success(articleService.searchArticleByCondition(curPage, size, searchCondition, conditionValue));
        } else {

            return Result.success(articleService.articlesInfo(curPage, size));
        }
    }
}

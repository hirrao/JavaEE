package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Article;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {
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

        System.out.println("modifyArticleInfo id:" + id);
        System.out.println("modifyArticleInfo title:" + title);
        System.out.println("modifyArticleInfo description:" + description);
        System.out.println("modifyArticleInfo image:" + image);
        System.out.println("modifyArticleInfo content:" + content);

        articleService.modifyArticleInfo(id, title, description, image, content);
        return Result.success();
    }

    @PostMapping("/deleteArticle")
    public Result deleteArticle(@RequestBody Map<String, String> map){
        var id = Long.parseLong(map.get("id"));
        articleService.deleteArticle(id);
        System.out.println("delete id:" + id);
        return Result.success();
    }

    @PostMapping("/searchArticleByCondition")
    public Result searchArticleByCondition(@RequestBody Map<String, String> map){
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var searchCondition = map.get("searchCondition");
        var conditionValue = map.get("conditionValue");

        System.out.println("curPage:" + curPage);
        System.out.println("size:" + size);
        System.out.println("searchCondition:" + searchCondition);
        System.out.println("conditionValue:" + conditionValue);

        if(!searchCondition.isEmpty() && !conditionValue.isEmpty()){
//            return Result.success();
            return Result.success(articleService.searchArticleByCondition(curPage, size, searchCondition, conditionValue));
        } else {

            return Result.success(articleService.articlesInfo(curPage, size));
        }
    }
}

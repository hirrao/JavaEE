package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Article;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }

    @RequestMapping("/add")
    public Result addArticle(@RequestBody Map<String, String> map) {
        long id = Long.parseLong(map.get("id"));
        String title = map.get("title");
        String description = map.get("description");
        String image = map.get("image");
        String content = map.get("content");
        articleService.addArticle(id, title, description, image, content);
        return Result.success();
    }
}

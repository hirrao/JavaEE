package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ArticleService {
    //返回全部文章
    List<Article> findAll();

    //根据文章id查询文章
    Article findById(long id);

    //添加文章
    void addArticle(long id, String title, String description, String image, String content);

    //更新文章
    void update(long id, String title, String description, String image, String content);

    //删除文章
    void delete(long id);
}

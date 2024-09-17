package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ArticleService {
    //返回全部文章
    List<Article> findAll();

    //根据文章id查询文章
    Article findById(long id);

    //添加文章
    void addArticle(String title, String description, String image, String content);


    void deleteArticle(long id);

    void modifyArticleInfo(long id, String title, String description, String image, String content);

    IPage<Article> articlesInfo(int curPage, int size);

    IPage<Article> searchArticleByCondition(int curPage, int size, String searchCondition, String conditionValue);
}

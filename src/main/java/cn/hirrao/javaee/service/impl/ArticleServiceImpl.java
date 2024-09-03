package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.Article;
import cn.hirrao.javaee.mapper.ArticleMapper;
import cn.hirrao.javaee.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;


    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }
    @Override
    public Article findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public void addArticle(long id, String title, String description, String image, String content) {
        articleMapper.addArticle(id, title, description, image, content);
    }

    @Override
    public void update(long id, String title, String description, String image, String content) {
        articleMapper.update(id, title, description, image, content);
    }

    @Override
    public void delete(long id) {
        articleMapper.delete(id);
    }
}

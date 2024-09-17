package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.Article;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.mapper.ArticleMapper;
import cn.hirrao.javaee.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
    public void addArticle(String title, String description, String image, String content) {
        articleMapper.addArticle(title, description, image, content);
    }


    @Override
    public void deleteArticle(long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public void modifyArticleInfo(long id, String title, String description, String image, String content) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setDescription(description);
        article.setImage(image);
        article.setContent(content);
        articleMapper.updateById(article);
    }

    @Override
    public IPage<Article> articlesInfo(int curPage, int size) {
//        PageHelper.startPage(curPage, size);
//        List<Article> articles = articleMapper.findAll();
//        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        Page<Article> page = new Page<>(curPage, size);
        return articleMapper.selectPage(page,null);
    }

    @Override
    public IPage<Article> searchArticleByCondition(int curPage, int size, String searchCondition, String conditionValue) {
//        PageHelper.startPage(curPage, size);
//        List<Article> articles;
//        switch (searchCondition) {
//            case "title":
//                articles = articleMapper.findByTitle(conditionValue);
//                break;
//            case "description":
//                articles = articleMapper.findByDescription(conditionValue);
//                break;
//            default:
//                articles = new ArrayList<>();
//        }
//        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        Page<Article> page = new Page<>(curPage, size);
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(searchCondition,conditionValue);


        return articleMapper.selectPage(page, queryWrapper);
    }
}


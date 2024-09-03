package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.Blog;
import cn.hirrao.javaee.mapper.BlogMapper;
import cn.hirrao.javaee.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogMapper blogMapper;

    @Autowired
    private BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper =  blogMapper;
    }

    @Override
    public Blog findByBlogId(Long bolgId) {
        return blogMapper.findByBlogId(bolgId);
    }

    @Override
    public List<Blog> findByUid(Long uid) {
        return blogMapper.findByUid(uid);
    }

    @Override
    public List<Blog> findByTitle(String title) {
        return blogMapper.findByTitle(title);
    }

    @Override
    public void addBlog(Long blogId, String content, String createTime, String updateTime, Long uid, String title) {
        blogMapper.addBlog(blogId, content, createTime, updateTime, uid, title);
    }

    @Override
    public void update(Long blogId, String content, String updateTime, String title) {
        blogMapper.update(blogId, content, updateTime, title);
    }

    @Override
    public void delete(Long blogId) {
        blogMapper.delete(blogId);
    }
}
package com.hirrao.javaee.service;

import com.hirrao.javaee.entity.Blog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    Blog findByBlogId(Long bolgId);

    List<Blog> findByUid(Long uid);

    List<Blog> findByTitle(String title);

    void addBlog(Long blogId, String content, String createTime, String updateTime, Long uid, String title);

    void update(Long blogId, String content, String updateTime, String title);

    void delete(Long blogId);

    IPage<Blog> search(int curPage,int size, Long uid);
}

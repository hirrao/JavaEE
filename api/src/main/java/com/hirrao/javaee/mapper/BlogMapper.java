package com.hirrao.javaee.mapper;

import com.hirrao.javaee.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {
    @Select("select * from blog where blogId = #{blogId}")
    Blog findByBlogId(Long blogId);

    @Select("select * from blog where uid = #{uid}")
    List<Blog> findByUid(Long uid);

    @Select("select * from blog where title = #{title}")
    List<Blog> findByTitle(String title);

    @Insert("insert into blog(blogId, content, createTime, updateTime, uid, title) values(#{blogId}, #{content}, #{createTime}, #{updateTime}, #{uid}, #{title})")
    void addBlog(Long blogId, String content, String createTime, String updateTime, Long uid, String title);

    @Update("update blog set content=#{content}, updateTime=#{updateTime}, title=#{title} where blogId=#{blogId}")
    void update(Long blogId, String content, String updateTime, String title);

    @Delete("DELETE FROM blog WHERE blogId = #{blogId}")
    void delete(Long blogId);
}

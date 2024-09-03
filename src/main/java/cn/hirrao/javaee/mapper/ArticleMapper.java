package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    @Select("SELECT * FROM articles")
    List<Article> findAll();

    @Select("SELECT * FROM articles WHERE id = #{id}")
    Article findById(long id);

    @Insert("INSERT INTO articles(id, title, description, image, content) VALUES(#{id}, #{title}, #{description}, #{image}, #{content})")
    void addArticle(long id, String title, String description, String image, String content);

    @Update("UPDATE articles SET title = #{title}, description = #{description}, image = #{image}, content = #{content} WHERE id = #{id}")
    void update(long id, String title, String description, String image, String content);

    @Delete("DELETE FROM articles WHERE id = #{id}")
    void delete(long id);
}

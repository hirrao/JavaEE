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

    @Select("SELECT * FROM articles WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<Article> findByTitle(String title);

    @Select("SELECT * FROM articles WHERE description LIKE CONCAT('%', #{description}, '%')")
    List<Article> findByDescription(String description);

    @Insert("INSERT INTO articles(title, description, image, content) VALUES(#{title}, #{description}, #{image}, #{content})")
    void addArticle(String title, String description, String image, String content);

    @Update("UPDATE articles SET title = #{title}, description = #{description}, image = #{image}, content = #{content} WHERE id = #{id}")
    int updateById(Article article);

    @Delete("DELETE FROM articles WHERE id = #{id}")
    void deleteById(long id);
}

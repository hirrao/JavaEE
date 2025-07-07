package com.hirrao.javaee.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("articles")
public class Article {
    @Id
    private Long id;
    private Long articleId;
    private String title;
    private String description;
    private String image;
    private String content;


    // getters and setters
}

package cn.hirrao.javaee.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
public class Article {
    @Id
    private Long id;

    private String title;
    private String description;
    private String image;
    private String content;

    // getters and setters
}

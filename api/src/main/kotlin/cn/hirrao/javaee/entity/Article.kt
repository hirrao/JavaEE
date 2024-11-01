package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("articles")
class Article {
    @Id
    var id: Long? = null

    var title: String? = null
    var description: String? = null
    var image: String? = null
    var content: String? = null // getters and setters
}

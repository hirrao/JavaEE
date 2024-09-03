package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

@Getter
@Setter
@AllArgsConstructor
public class Blog {
    @TableId(value = "blogId")
    private Long blogId;
    private String content;
    private String createTime;
    private String updateTime;
    private Long uid;
    private String title;
}

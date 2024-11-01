package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField("content")
    private String content;
    @TableField("createTime")
    private String createTime;
    @TableField("updateTime")
    private String updateTime;
    private Long uid;
    private String title;
}

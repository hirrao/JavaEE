package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserIntro {
    @TableId(value = "uid")
    private Long uid;
    @TableField("intro")
    private String intro;
}

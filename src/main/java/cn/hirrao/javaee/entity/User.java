package cn.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


//JsonIgnore注解是jackson包中的注解，用于返回json数据时，忽略该字段
@Getter
@Setter
@AllArgsConstructor
public class User {
    @TableId(value = "uid")
    private Long uid;
    private String userName;
    @JsonIgnore
    private String phoneNumber;
    private String userPassword;
    private String sex;
    private String birthday;
    private int permission;
}

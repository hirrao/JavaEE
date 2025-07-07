package com.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class User {
    @TableId
    private Long id;
    private Long uid;
    private String userName;
    private String email;
    private String saltPassword;
    private int sex;
    private String birthday;
    private int permission;
}

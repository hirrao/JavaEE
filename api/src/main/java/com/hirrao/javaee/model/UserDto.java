package com.hirrao.javaee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long uid;
    private String userName;
    private String email;
    private String sex;
    private String birthday;
    private int permission;
}

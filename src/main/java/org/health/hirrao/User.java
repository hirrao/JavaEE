package org.health.hirrao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private long id;
    private String userName;
    private String phoneNumber;
    private String userPassword;
    private String sex;
    private Date birthday;
    private int permission;
    public User() {

    }
}

package org.health.hirrao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private long id;
    private String userName;
    private String phoneNumber;
    private String userPassword;
    private String sex;
    private LocalDate birthday;
    private int permission;

    public User() {

    }

    public HashMap<String, Object> toMap() {
        var map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("userName", userName);
        map.put("phoneNumber", phoneNumber);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("permission", permission);
        return map;
    }
}

package cn.hirrao.javaee.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
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

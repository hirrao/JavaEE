package cn.hirrao.javaee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private long uid;
    private String userName;
    @JsonIgnore
    private String phoneNumber;
    private String userPassword;
    private String sex;
    private LocalDate birthday;
    private int permission;

    public User() {

    }
}

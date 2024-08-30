package cn.hirrao.javaee.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

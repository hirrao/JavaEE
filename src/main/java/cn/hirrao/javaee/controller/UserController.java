package cn.hirrao.javaee.controller;


import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.Jwt;
import cn.hirrao.javaee.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cn.hirrao.javaee.utils.Jwt.createToken;

@RestController
@RequestMapping("/user")
public class UserController {

    final SnowFlake snowFlake = new SnowFlake(1, 1);
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String userName, String userPassword) {
        if (Objects.equals(userName, "") || Objects.equals(userPassword, "")) {
            return Result.error("用户名或密码不能为空");
        } else if (userName.length() < 3 || userName.length() > 20) {
            return Result.error("用户名长度必须在3-20之间");
        } else if (userPassword.length() < 6 || userPassword.length() > 20) {
            return Result.error("密码长度必须在6-20之间");
        } else if (!userName.matches("^[a-zA-Z0-9_]{3,20}$") || !userPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            return Result.error("用户名或密码只能包含字母、数字和下划线");
        }
        User u = userService.findByUsername(userName);
        if (u == null) {
            long uid = snowFlake.nextId();
            userService.register(uid, userName, userPassword);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(String userName, String userPassword) {
        if (Objects.equals(userName, "") || Objects.equals(userPassword, "")) {
            return Result.error("用户名或密码不能为空");
        }
        User user = userService.findByUsername(userName);
        if (user == null) {
            return Result.error("用户名不存在");
        } else {
            if (userPassword.equals(user.getUserPassword())) {
                //密码正确，根据用户的uid和用户名生成token
                String token = createToken(user);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
    }

    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户的token获取用户信息
        String uid = Jwt.parseToken(token);
        User user = userService.findByUid(Long.parseLong(uid));
        return Result.success(user);
    }
}

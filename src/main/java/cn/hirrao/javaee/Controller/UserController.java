package cn.hirrao.javaee.Controller;


import cn.hirrao.javaee.Entity.Result;
import cn.hirrao.javaee.Entity.User;
import cn.hirrao.javaee.Service.UserService;
import cn.hirrao.javaee.Utils.JwtUtil;
import cn.hirrao.javaee.Utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    private UserService userService;

    SnowFlake snowFlake = new SnowFlake(1, 1);
    @PostMapping("/register")
    public Result register(String userName,String userPassword) {
        if(userName == "" || userPassword == "") {
            return Result.error("用户名或密码不能为空");
        } else if(userName.length() < 3 || userName.length() > 20) {
            return Result.error("用户名长度必须在3-20之间");
        } else if(userPassword.length() < 6 || userPassword.length() > 20) {
            return Result.error("密码长度必须在6-20之间");
        } else if (!userName.matches("^[a-zA-Z0-9_]{3,20}$")||!userPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            return Result.error("用户名或密码只能包含字母、数字和下划线");
        }
        User u = userService.findByUsername(userName);
        if (u == null) {
            long uid = snowFlake.nextId();
            userService.register(uid,userName, userPassword);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(String userName, String userPassword) {
        if(userName == "" || userPassword == "") {
            return Result.error("用户名或密码不能为空");
        }
        User u = userService.findByUsername(userName);
        if(u == null) {
            return Result.error("用户名不存在");
        } else {
            String p1 = DigestUtils.md5DigestAsHex(userPassword.getBytes());
            String p2 = DigestUtils.md5DigestAsHex(p1.getBytes());
            if(p2.equals(u.getUserPassword())) {
                //密码正确，根据用户的uid和用户名生成token
                Map<String,Object> claims = new HashMap<>();
                claims.put("uid", u.getId());
                claims.put("userName", u.getUserName());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("密码错误");
            }
        }
    }
}

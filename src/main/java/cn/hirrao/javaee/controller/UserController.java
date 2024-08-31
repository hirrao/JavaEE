package cn.hirrao.javaee.controller;


import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.RedisService;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.MobileMessage;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cn.hirrao.javaee.utils.Jwt.createToken;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SnowFlake snowFlake = new SnowFlake(1, 1);
    private final UserService userService;
    private final RedisService redisService;

    @Autowired
    UserController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @PostMapping("/register")
    public Result register(String userName, String userPassword, String phoneNumber, String messageCode) {
        if (userName == null || userPassword == null || !userName.matches("^[a-zA-Z0-9_]{3,20}$") || !userPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            return Result.error(101, "非法用户名或密码");
        }
        User user = userService.findByUsername(userName);
        if (user != null) {
            return Result.error(102, "用户名已被占用");
        }
        if (redisService.get(phoneNumber) == null) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
        long uid = snowFlake.nextId();
        userService.register(uid, userName, userPassword, phoneNumber);
        return Result.success();
    }

    @PostMapping("/messageSend")
    public Result messageSend(String phoneNumber) {
        User user = userService.findByPhoneNumber(phoneNumber);
        if (user != null) {
            return Result.error(103, "手机号已被占用");
        }
        String code = MobileMessage.generateCode();
        if (!MobileMessage.sendMessage(phoneNumber, code)) {
            return Result.error(110, "验证码发送失败, 请稍后重试");
        } else {
            redisService.set(phoneNumber, code);
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result login(String userName, String userPassword) {
        if (Objects.equals(userName, "") || Objects.equals(userPassword, "")) {
            return Result.error(104, "用户名或密码错误");
        }
        User user = userService.findByUsername(userName);
        if (user == null) {
            return Result.error(104, "用户名或密码错误");
        } else {
            String password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
            if (password.equals(user.getUserPassword())) {
                //密码正确，根据用户的uid和用户名生成token
                String token = createToken(user);
                return Result.success(token);
            } else {
                return Result.error(104, "密码错误");
            }
        }
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        User user = ThreadLocalUtil.get();
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }
}

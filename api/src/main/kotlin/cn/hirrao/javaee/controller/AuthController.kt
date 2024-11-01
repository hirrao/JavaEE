package cn.hirrao.javaee.controller;


import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.RedisService;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.StringUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static cn.hirrao.javaee.utils.Jwt.createToken;

@RestController
@RequestMapping("/user/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SnowFlake snowFlake = new SnowFlake(1, 1);
    private final UserService userService;
    private final RedisService redisService;

    @Autowired
    AuthController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @PostMapping("/message")
    public Result message(@RequestBody Map<String, String> map) {
        /*
        logger.debug("/message接受请求{}", map);
        String userName = map.get("userName");
        String phoneNumber = map.get("phoneNumber");
        String messageCode = map.get("messageCode");
        if (StringUtil.isEmpty(userName) || !userName.matches("^[a-zA-Z0-9_]{3,20}$")) {
            return Result.error(101, "非法用户名或密码");
        }
        User user = userService.findByUsername(userName);
        if (user != null) {
            return Result.error(102, "用户名已被占用");
        }
        if (StringUtil.isEmpty(redisService.get(phoneNumber))) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
        return Result.success("发送成功！");
         */
        return Result.success();
    }


    //查找手机号或者名字是否已被占用
    @PostMapping("/find")
    public Result find(@RequestBody Map<String, String> map) {
        logger.debug("/find接受请求{}", map);
        var userName = map.get("userName");
        var phoneNumber = map.get("phoneNumber");
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(phoneNumber)) {
            return Result.error(101, "非法用户名或手机号");
        }
        var user = userService.findByUsername(userName);
        if (user != null) {
            return Result.error(102, "用户名或手机号已被占用");
        }
        var user2 = userService.findByPhoneNumber(phoneNumber);
        if (user2 != null) {
            return Result.error(103, "用户名或手机号已被占用");
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> map) {
        logger.debug("/register接受请求{}", map);
        var userName = map.get("userName");
        var userPassword = map.get("userPassword");
        var phoneNumber = map.get("phoneNumber");
        var messageCode = map.get("messageCode");
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword) || !userName.matches("^[a-zA-Z0-9_]{3,20}$") || !userPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            return Result.error(101, "非法用户名或密码");
        }
        var user = userService.findByUsername(userName);
        if (user != null) {
            return Result.error(102, "用户名已被占用");
        }
        /*
        if (StringUtil.isEmpty(redisService.get(phoneNumber))) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
         */
        var uid = snowFlake.nextId();
        var password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        userService.register(uid, userName, password, phoneNumber);
        logger.info("注册成功 用户名:{} 手机号:{}", userName, phoneNumber);
        return Result.success();
    }

    @PostMapping("/messageSend")
    public Result messageSend(@RequestBody Map<String, String> map) {
        /* 发送短信验证码, 服务关闭后展示禁用
        logger.debug("/messageSend接受请求{}", map);
        String phoneNumber = map.get("phoneNumber");
        if (StringUtil.isEmpty(phoneNumber) || !phoneNumber.matches("^1[3-9]\\d{9}$")) {
            return Result.error(101, "非法手机号");
        }
        String code = MobileMessage.generateCode();
        switch (MobileMessage.sendMessage(phoneNumber, code, "20")) {
            case -1:
                return Result.error(110, "验证码发送失败, 请稍后重试");
            case -2:
                return Result.error(113, "手机号状态异常, 请检查手机号是否正确与正常使用");
            case 0:
                redisService.set(phoneNumber, code);
                logger.info("发送验证码成功 手机号:{}", phoneNumber);
                return Result.success();
            default:
                return Result.error(110, "未知内部错误");
        }
         */
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        logger.debug("/login接受请求{}", map);
        var userName = map.get("userName");
        var userPassword = map.get("userPassword");
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword)) {
            return Result.error(104, "用户名或密码错误");
        }
        var user = userService.findByUsername(userName);
        if (user == null) {
            return Result.error(104, "用户名或密码错误");
        } else {
            if (userPassword.equals(user.getUserPassword())) {
                //密码正确，根据用户的uid和用户名生成token
                var tokens = createToken(user);
                @Getter
                class Data {
                    private final int permission = user.getPermission();
                    private final String token = tokens;
                }
                var data = new Data();
                if (data.getPermission() == -1) return Result.error(106, "该用户已被封禁");
                return Result.success(data);
            } else {
                return Result.error(104, "用户名密码错误");
            }
        }
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody Map<String, String> map) {
        logger.debug("/resetPassword接受请求{}", map);
        var phoneNumber = map.get("phoneNumber");
        var messageCode = map.get("messageCode");
        var newPassword = map.get("newPassword");
        if (StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(messageCode) || StringUtil.isEmpty(newPassword) || !newPassword.matches("^[a-zA-Z0-9_]{6,20}$")) {
            return Result.error(101, "非法手机号或验证码或密码");
        }
        var user = userService.findByPhoneNumber(phoneNumber);
        var password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if (user == null) {
            return Result.error(105, "用户不存在");
        }
        /*
        if (redisService.get(phoneNumber) == null) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
         */
        userService.updatePassword(user.getUid(), password);
        logger.info("重置密码成功 用户名:{} 手机号:{}", user.getUserName(), phoneNumber);
        return Result.success();
    }
}

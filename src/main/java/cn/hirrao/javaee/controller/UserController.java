package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        User user = ThreadLocalUtil.get();
        logger.debug("获取用户信息{}", user);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Map<String, String> map) {
        logger.debug("更新用户信息{}", map);
        var uid = Long.parseLong(map.get("uid"));
        var phoneNumber = map.get("phoneNumber");
        var sex = map.get("sex");
        var birthday = map.get("birthday");
        userService.update(uid, phoneNumber, sex, birthday);
        return Result.success();
    }
}

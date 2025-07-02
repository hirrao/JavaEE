package com.hirrao.javaee.controller;

import com.hirrao.javaee.entity.Result;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.service.UserService;
import com.hirrao.javaee.utils.ThreadLocalUtil;
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
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var phoneNumber = map.get("phoneNumber");
        var sex = map.get("sex");
        var birthday = map.get("birthday");
        userService.update(uid, phoneNumber, sex, birthday);
        return Result.success();
    }

    @PostMapping("/accountsInfo")
    public Result accountsInfo(@RequestBody Map<String, String> map) {
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        return Result.success(userService.accountsInfo(curPage, size));
    }

    @PostMapping("/modifyUserInfo")
    public Result modifyUserInfo(@RequestBody Map<String, String> map) {
//        User user = ThreadLocalUtil.get();
//        var uid = user.getUid();
        var uid = Long.parseLong(map.get("uid"));
        var userName = map.get("userName");
        var phoneNumber = map.get("phoneNumber");
        var sex = map.get("sex");
        var birthday = map.get("birthday");
        var permission = Integer.parseInt(map.get("permission"));
        logger.debug("modifyUserInfo uid:" + uid);
        logger.debug("modifyUserInfo userName:" + userName);
        logger.debug("modifyUserInfo sex:" + sex);
        logger.debug("modifyUserInfo birthday:" + birthday);
        logger.debug("modifyUserInfo permission:" + permission);

        userService.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission);
        return Result.success();
    }

    @PostMapping("/updateUserName")
    public Result updateUserName(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        String userName = map.get("userName");
        userService.updateUserName(uid, userName);
        return Result.success();
    }

    @PostMapping("/updateSex")
    public Result updateSex(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        String sex = map.get("sex");
        userService.updateSex(uid, sex);
        return Result.success();
    }

    @PostMapping("/updateBirthday")
    public Result updateBirthday(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        String birthday = map.get("birthday");
        userService.updateBirthday(uid, birthday);
        return Result.success();
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody Map<String, String> map) {
//        logger.debug(map.size());
//        User user = ThreadLocalUtil.get();
        var uid = Long.parseLong(map.get("uid"));
        userService.deleteUser(uid);
        logger.debug("delete uid:" + uid);
        return Result.success();
    }

    @PostMapping("/searchUserByCondition")
    public Result searchUserByCondition(@RequestBody Map<String, String> map) {
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var searchCondition = map.get("searchCondition");
        var conditionValue = map.get("conditionValue");
        logger.debug("curPage:" + curPage);
        logger.debug("size:" + size);
        logger.debug("searchCondition:" + searchCondition);
        logger.debug("conditionValue:" + conditionValue);
        if (!searchCondition.isEmpty() && !conditionValue.isEmpty()) {
            return Result.success(userService.searchUserByCondition(curPage, size, searchCondition, conditionValue));
        } else {
            return Result.success(userService.accountsInfo(curPage, size));
        }
    }
}

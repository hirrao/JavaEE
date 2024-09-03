package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @PostMapping("/accountsInfo")
    public Result accountsInfo(@RequestBody Map<String, String> map){
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        return Result.success(userService.accountsInfo(curPage,size));
    }

    @PostMapping("/modifyUserInfo")
    public Result modifyUserInfo(@RequestBody Map<String, String> map) {
        var uid = Long.parseLong(map.get("uid"));
        var userName = map.get("userName");
        var sex = map.get("sex");
        var birthday = map.get("birthday");
        var permission = Integer.parseInt(map.get("permission"));
        System.out.println("modifyUserInfo uid:"+uid);
        System.out.println("modifyUserInfo userName:"+userName);
        System.out.println("modifyUserInfo sex:"+sex);
        System.out.println("modifyUserInfo birthday:"+birthday);
        System.out.println("modifyUserInfo permission:"+permission);

        userService.modifyUserInfo(uid, userName, sex, birthday,permission);
        return Result.success();
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody Map<String, String> map){
        System.out.println(map.size());
        var uid=Long.parseLong(map.get("uid"));
        userService.deleteUser(uid);
        System.out.println("delete uid:"+uid);
        return Result.success();
    }

    @PostMapping("/searchUserByCondition")
    public Result searchUserByCondition(@RequestBody Map<String, String> map){
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        var searchCondition=map.get("searchCondition");
        var conditionValue=map.get("conditionValue");
        System.out.println("curPage:"+curPage);
        System.out.println("size:"+size);
        System.out.println("searchCondition:"+searchCondition);
        System.out.println("conditionValue:"+conditionValue);
        if(!searchCondition.isEmpty() &&!conditionValue.isEmpty()){
            return Result.success(userService.searchUserByCondition(curPage,size,searchCondition,conditionValue));
        }else{
            return Result.success(userService.accountsInfo(curPage,size));
        }
    }
}

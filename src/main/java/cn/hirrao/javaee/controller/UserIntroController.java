package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.UserIntro;
import cn.hirrao.javaee.service.UserIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/intro")
public class UserIntroController {
    private final UserIntroService userIntroService;

    @Autowired
    public UserIntroController(UserIntroService userIntroService) {
        this.userIntroService = userIntroService;
    }

    @PostMapping("/get")
    public Result get(@RequestBody Map<String, String> map) {
        Long uid = Long.parseLong(map.get("uid"));
        UserIntro userIntro = userIntroService.findUserIntro(uid);
        if (userIntro == null) userIntroService.setUserIntro(uid, "");
        userIntro = userIntroService.findUserIntro(uid);
        return Result.success(userIntro);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String, String> map) {
        Long uid = Long.parseLong(map.get("uid"));
        String userIntro = map.get("intro");
        userIntroService.updateUserIntro(uid, userIntro);
        return Result.success("修改成功");
    }
}

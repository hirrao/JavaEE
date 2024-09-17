package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.entity.UserIntro;
import cn.hirrao.javaee.service.UserIntroService;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/intro")
public class UserIntroController {
    private final UserIntroService userIntroService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserIntroController(UserIntroService userIntroService) {
        this.userIntroService = userIntroService;
    }

    @GetMapping("/get")
    public Result get() {
        User user = ThreadLocalUtil.get();
        long uid = user.getUid();
        UserIntro userIntro = userIntroService.findUserIntro(uid);
        if (userIntro == null) userIntroService.setUserIntro(uid, "");
        userIntro = userIntroService.findUserIntro(uid);
        return Result.success(userIntro);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String, String> map) {
        logger.debug("intro/update接受请求 {}", map);
        Long uid = Long.parseLong(map.get("uid"));
        String userIntro = map.get("intro");
        userIntroService.updateUserIntro(uid, userIntro);
        return Result.success("修改成功");
    }
}

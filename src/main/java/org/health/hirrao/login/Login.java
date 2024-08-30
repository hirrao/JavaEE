package org.health.hirrao.login;

import org.health.hirrao.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.health.hirrao.token.Create.createToken;

@RestController
public class Login {
    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password) {
        var map = new HashMap<String, Object>();
        //TODO 鉴权是否正确登录, 目前暂时使用测试User对象
        var user = new User("114514", "WJC", "WJC@WJC.com");
        map.put("token", createToken(user));
        map.put("user", user);
        map.put("status", "success");
        return map;
    }
}

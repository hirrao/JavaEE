package org.health.hirrao.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.health.hirrao.token.Prase.praseToken;

@RestController
public class TokenLogin {
    @PostMapping("/tokenLogin")
    public Object tokenLogin(@RequestParam String token) {
        var map = new HashMap<String, Object>();
        if (token == null || token.isEmpty()) {
            map.put("status", "fail");
            map.put("code", "401");
            return map;
        }
        try {
            var id = praseToken(token);
            map.put("status", "success");
            map.put("id", id);
        }
        catch (Exception e) {
            map.put("status", "fail");
            map.put("code", "401");
            map.put("message","token is invalid");
            return map;
        }
        return map;
    }
}

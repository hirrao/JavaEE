package org.health.hirrao;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Test {
    @GetMapping("/test")
    public User hello(){
        var wjc=new User();
        wjc.setName("WJC");
        wjc.setEmail("WJC@WJC.com");
        return wjc;
    }

    @PostMapping("/update")
    public User update(@RequestBody User user) {
        user.setName(user.getName() + "-updated");
        user.setEmail(user.getEmail() + "-updated");
        return user;
    }
}

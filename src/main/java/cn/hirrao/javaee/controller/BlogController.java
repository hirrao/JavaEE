package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Blog;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.BlogService;
import cn.hirrao.javaee.service.UserService;
import cn.hirrao.javaee.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class BlogController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SnowFlake snowFlake = new SnowFlake(1, 1);
    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    private BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/userBlogInfo")
    public Result userBlogInfo(@RequestBody Map<String, String> map) {
        long uid = Long.parseLong(map.get("uid"));
        List<Blog> blog = blogService.findByUid(uid);
        return Result.success(blog);
    }

    @PostMapping("/blogInfo")
    public Result blogInfo(@RequestBody Map<String, String> map) {
        long blogId = Long.parseLong(map.get("blogId"));
        Blog blog = blogService.findByBlogId(blogId);
        return Result.success(blog);
    }

    @PostMapping("/userInfo")
    public Result userInfo(@RequestBody Map<String, String> map) {
        long uid = Long.parseLong(map.get("uid"));
        User user = userService.findByUid(uid);
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> map) {
        long uid = Long.parseLong(map.get("uid"));
        long blogId = snowFlake.nextId();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = now.format(formatter);
        String content = map.get("content");
        String title = map.get("title");
        blogService.addBlog(blogId, content, time, time, uid, title);
        return Result.success();
    }

}

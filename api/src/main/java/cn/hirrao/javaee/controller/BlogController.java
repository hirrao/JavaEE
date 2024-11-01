package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.BlogService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class BlogController {
    private final SnowFlake snowFlake = new SnowFlake(1, 1);
    private final BlogService blogService;

    @Autowired
    private BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/userBlogInfo")
    public Result userBlogInfo() {
        var user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var blog = blogService.findByUid(uid);
        return Result.success(blog);
    }

    @PostMapping("/blogInfo")
    public Result blogInfo(@RequestBody Map<String, String> map) {
        var blogId = Long.parseLong(map.get("blogId"));
        var blog = blogService.findByBlogId(blogId);
        return Result.success(blog);
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        var user = ThreadLocalUtil.get();
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> map) {
        var user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var blogId = snowFlake.nextId();
        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var time = now.format(formatter);
        var content = map.get("content");
        var title = map.get("title");
        blogService.addBlog(blogId, content, time, time, uid, title);
        return Result.success();
    }

    @PostMapping("/search")
    public Result searchUserByCondition(@RequestBody Map<String, String> map) {
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var user = ThreadLocalUtil.get();
        var uid = user.getUid();
        return Result.success(blogService.search(curPage, size, uid));
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String, String> map) {
        var blogId = Long.parseLong(map.get("blogId"));
        blogService.delete(blogId);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String, String> map) {
        var blogId = Long.parseLong(map.get("blogId"));
        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var time = now.format(formatter);
        var content = map.get("content");
        var title = map.get("title");
        blogService.update(blogId, content, time, title);
        return Result.success();
    }
}

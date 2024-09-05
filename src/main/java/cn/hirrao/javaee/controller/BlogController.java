package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Blog;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.service.BlogService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        List<Blog> blog = blogService.findByUid(uid);
        return Result.success(blog);
    }

    @PostMapping("/blogInfo")
    public Result blogInfo(@RequestBody Map<String, String> map) {
        long blogId = Long.parseLong(map.get("blogId"));
        Blog blog = blogService.findByBlogId(blogId);
        return Result.success(blog);
    }

    @GetMapping("/userInfo")
    public Result userInfo() {
        User user = ThreadLocalUtil.get();
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        long blogId = snowFlake.nextId();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = now.format(formatter);
        String content = map.get("content");
        String title = map.get("title");
        blogService.addBlog(blogId, content, time, time, uid, title);
        return Result.success();
    }

    @PostMapping("/search")
    public Result searchUserByCondition(@RequestBody Map<String, String> map) {
        int curPage = Integer.parseInt(map.get("curPage"));
        int size = Integer.parseInt(map.get("size"));
        Long uid = Long.parseLong(map.get("uid"));
        return Result.success(blogService.search(curPage, size, uid));
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String, String> map) {
        Long blogId = Long.parseLong(map.get("blogId"));
        blogService.delete(blogId);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String, String> map) {
        Long blogId = Long.parseLong(map.get("blogId"));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = now.format(formatter);
        String content = map.get("content");
        String title = map.get("title");
        blogService.update(blogId, content, time, title);
        return Result.success();
    }
}

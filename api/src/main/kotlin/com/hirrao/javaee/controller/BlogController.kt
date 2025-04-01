package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.BlogService
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.SnowFlake
import com.hirrao.javaee.utils.ThreadLocalUtil.get
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/profile")
class BlogController @Autowired private constructor(private val blogService: BlogService) {
    private val snowFlake = SnowFlake(1, 1)

    data class DataAdd(val title: String, val content: String)
    data class DataSearch(val curPage: Int, val size: Int)
    data class DataUpdate(val blogId: Long, val content: String, val title: String)

    @GetMapping("/userBlogInfo")
    fun userBlogInfo(): Result {
        val user = get()
        val uid = user.uid
        val blog = blogService.findByUid(uid)
        return success(blog)
    }

    @PostMapping("/blogInfo")
    fun blogInfo(blogId: Long): Result {
        val blog = blogService.findByBlogId(blogId)
        return success(blog)
    }

    @GetMapping("/userInfo")
    fun userInfo(): Result {
        val user = get()
        return success(user)
    }

    @PostMapping("/add")
    fun add(dataAdd: DataAdd): Result {
        val (title, content) = dataAdd
        val user = get()
        val uid = user.uid
        val blogId = snowFlake.nextId()
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = now.format(formatter)
        blogService.addBlog(blogId, content, time, time, uid, title)
        return success()

    }

    @PostMapping("/search")
    fun searchUserByCondition(dataSearch: DataSearch): Result {
        val (curPage, size) = dataSearch
        val user = get()
        val uid = user.uid
        return success(blogService.search(curPage, size, uid))
    }

    @PostMapping("/delete")
    fun delete(@RequestBody blogId: Long): Result {
        blogService.delete(blogId)
        return success()
    }

    @PostMapping("/update")
    fun update(@RequestBody dataUpdate: DataUpdate): Result {
        val (blogId, content, title) = dataUpdate
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val time = now.format(formatter)
        blogService.update(blogId, content, time, title)
        return success()
    }
}

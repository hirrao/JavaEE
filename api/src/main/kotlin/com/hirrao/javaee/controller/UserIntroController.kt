package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.UserIntroService
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.ThreadLocalUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/intro")
class UserIntroController @Autowired constructor(private val userIntroService: UserIntroService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    data class DataUpdate(val uid: Long, val intro: String)

    @GetMapping("/get")
    fun get(): Result {
        val user = ThreadLocalUtil.get()
        val uid = user.uid
        var userIntro = userIntroService.findUserIntro(uid)
        if (userIntro == null) userIntroService.setUserIntro(uid, "")
        userIntro = userIntroService.findUserIntro(uid)
        return success(userIntro)
    }

    @PostMapping("/update")
    fun update(@RequestBody dataUpdate: DataUpdate): Result {
        logger.debug("intro/update接受请求 {}", dataUpdate)
        val (uid, userIntro) = dataUpdate
        userIntroService.updateUserIntro(uid, userIntro)
        return success("修改成功")
    }
}

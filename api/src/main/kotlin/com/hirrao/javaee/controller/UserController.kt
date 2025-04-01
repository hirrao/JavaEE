package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.UserService
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.ThreadLocalUtil.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    data class DataUpdate(val phoneNumber: String, val sex: String, val birthday: String)
    data class DataModifyUserInfo(
        val uid: Long,
        val userName: String,
        val phoneNumber: String,
        val sex: String,
        val birthday: String,
        val permission: Int
    )

    data class DataSearchUserByCondition(
        val curPage: Int, val size: Int, val searchCondition: String, val conditionValue: String
    )

    @GetMapping("/userInfo")
    fun userInfo(): Result {
        val user = get()
        logger.debug("获取用户信息{}", user)
        return success(user)
    }

    @PutMapping("/update")
    fun update(@RequestBody dataUpdate: DataUpdate): Result {
        val (phoneNumber, sex, birthday) = dataUpdate
        logger.debug("更新用户信息{}", dataUpdate)
        val user = get()
        val uid = user.uid
        userService.update(uid, phoneNumber, sex, birthday)
        return success()
    }

    @PostMapping("/accountsInfo")
    fun accountsInfo(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        return success(userService.accountsInfo(curPage, size))
    }

    @PostMapping("/modifyUserInfo")
    fun modifyUserInfo(@RequestBody dataModifyUserInfo: DataModifyUserInfo): Result {
        val (uid, userName, phoneNumber, sex, birthday, permission) = dataModifyUserInfo
        userService.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission)
        return success()
    }

    @PostMapping("/updateUserName")
    fun updateUserName(@RequestBody userName: String): Result {
        val user = get()
        val uid = user.uid
        userService.updateUserName(uid, userName)
        return success()
    }

    @PostMapping("/updateSex")
    fun updateSex(@RequestBody sex: String): Result {
        val user = get()
        val uid = user.uid
        userService.updateSex(uid, sex)
        return success()
    }

    @PostMapping("/updateBirthday")
    fun updateBirthday(@RequestBody birthday: String): Result {
        val user = get()
        val uid = user.uid
        userService.updateBirthday(uid, birthday)
        return success()
    }

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestBody uid: Long): Result {
        userService.deleteUser(uid)
        return success()
    }

    @PostMapping("/searchUserByCondition")
    fun searchUserByCondition(@RequestBody dataSearchUserByCondition: DataSearchUserByCondition): Result {
        val (curPage, size, searchCondition, conditionValue) = dataSearchUserByCondition
        return if (searchCondition.isNotEmpty() && conditionValue.isNotEmpty()) {
            success(
                userService.searchUserByCondition(
                    curPage, size, searchCondition, conditionValue
                )
            )
        } else {
            success(userService.accountsInfo(curPage, size))
        }
    }
}

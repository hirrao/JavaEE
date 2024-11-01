package cn.hirrao.javaee.controller

import cn.hirrao.javaee.entity.Result
import cn.hirrao.javaee.entity.Result.Companion.error
import cn.hirrao.javaee.entity.Result.Companion.success
import cn.hirrao.javaee.service.RedisService
import cn.hirrao.javaee.service.UserService
import cn.hirrao.javaee.utils.Jwt.createToken
import cn.hirrao.javaee.utils.SnowFlake
import cn.hirrao.javaee.utils.StringUtil.isEmpty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.DigestUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user/auth")
class AuthController @Autowired internal constructor(
    private val userService: UserService, private val redisService: RedisService
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val snowFlake = SnowFlake(1, 1)

    @PostMapping("/message")
    fun message(@RequestBody map: Map<String?, String?>?): Result {/*
        logger.debug("/message接受请求{}", map);
        String userName = map.get("userName");
        String phoneNumber = map.get("phoneNumber");
        String messageCode = map.get("messageCode");
        if (StringUtil.isEmpty(userName) || !userName.matches("^[a-zA-Z0-9_]{3,20}$")) {
            return Result.error(101, "非法用户名或密码");
        }
        User user = userService.findByUsername(userName);
        if (user != null) {
            return Result.error(102, "用户名已被占用");
        }
        if (StringUtil.isEmpty(redisService.get(phoneNumber))) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
        return Result.success("发送成功！");
         */
        return success()
    }


    //查找手机号或者名字是否已被占用
    @PostMapping("/find")
    fun find(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("/find接受请求{}", map)
        val userName = map["userName"]
        val phoneNumber = map["phoneNumber"]
        if (isEmpty(userName) || isEmpty(phoneNumber)) {
            return error(101, "非法用户名或手机号")
        }
        val user = userService.findByUsername(userName)
        if (user != null) {
            return error(102, "用户名或手机号已被占用")
        }
        val user2 = userService.findByPhoneNumber(phoneNumber)
        if (user2 != null) {
            return error(103, "用户名或手机号已被占用")
        }
        return success()
    }

    @PostMapping("/register")
    fun register(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("/register接受请求{}", map)
        val userName = map["userName"]
        val userPassword = map["userPassword"]
        val phoneNumber = map["phoneNumber"]
        val messageCode = map["messageCode"]
        if (isEmpty(userName) || isEmpty(userPassword) || !userName!!.matches("^[a-zA-Z0-9_]{3,20}$".toRegex()) || !userPassword!!.matches(
                "^[a-zA-Z0-9_]{6,20}$".toRegex()
            )
        ) {
            return error(101, "非法用户名或密码")
        }
        val user = userService.findByUsername(userName)
        if (user != null) {
            return error(102, "用户名已被占用")
        }/*
        if (StringUtil.isEmpty(redisService.get(phoneNumber))) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
         */
        val uid = snowFlake.nextId()
        val password = DigestUtils.md5DigestAsHex(userPassword!!.toByteArray())
        userService.register(uid, userName, password, phoneNumber)
        logger.info("注册成功 用户名:{} 手机号:{}", userName, phoneNumber)
        return success()
    }

    @PostMapping("/messageSend")
    fun messageSend(@RequestBody map: Map<String?, String?>?): Result {/* 发送短信验证码, 服务关闭后展示禁用
        logger.debug("/messageSend接受请求{}", map);
        String phoneNumber = map.get("phoneNumber");
        if (StringUtil.isEmpty(phoneNumber) || !phoneNumber.matches("^1[3-9]\\d{9}$")) {
            return Result.error(101, "非法手机号");
        }
        String code = MobileMessage.generateCode();
        switch (MobileMessage.sendMessage(phoneNumber, code, "20")) {
            case -1:
                return Result.error(110, "验证码发送失败, 请稍后重试");
            case -2:
                return Result.error(113, "手机号状态异常, 请检查手机号是否正确与正常使用");
            case 0:
                redisService.set(phoneNumber, code);
                logger.info("发送验证码成功 手机号:{}", phoneNumber);
                return Result.success();
            default:
                return Result.error(110, "未知内部错误");
        }
         */
        return success()
    }

    @PostMapping("/login")
    fun login(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("/login接受请求{}", map)
        val userName = map["userName"]
        val userPassword = map["userPassword"]
        if (isEmpty(userName) || isEmpty(userPassword)) {
            return error(104, "用户名或密码错误")
        }
        val user = userService.findByUsername(userName)
        if (user == null) {
            return error(104, "用户名或密码错误")
        } else {
            if (userPassword == user.userPassword) {
                //密码正确，根据用户的uid和用户名生成token
                val tokens = createToken(user)

                data class Data(
                    val permission: Int = user.permission, val token: String = tokens,
                )

                val data = Data()
                if (data.permission == -1) return error(106, "该用户已被封禁")
                return success(data)
            } else {
                return error(104, "用户名密码错误")
            }
        }
    }

    @PostMapping("/resetPassword")
    fun resetPassword(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("/resetPassword接受请求{}", map)
        val phoneNumber = map["phoneNumber"]
        val messageCode = map["messageCode"]
        val newPassword = map["newPassword"]
        if (isEmpty(phoneNumber) || isEmpty(messageCode) || isEmpty(newPassword) || !newPassword!!.matches("^[a-zA-Z0-9_]{6,20}$".toRegex())) {
            return error(101, "非法手机号或验证码或密码")
        }
        val user = userService.findByPhoneNumber(phoneNumber)
        val password = DigestUtils.md5DigestAsHex(newPassword!!.toByteArray())
        if (user == null) {
            return error(105, "用户不存在")
        }/*
        if (redisService.get(phoneNumber) == null) {
            return Result.error(111, "请发送验证码");
        }
        if (!redisService.get(phoneNumber).equals(messageCode)) {
            return Result.error(112, "验证码错误");
        }
         */
        userService.updatePassword(user.uid, password)
        logger.info("重置密码成功 用户名:{} 手机号:{}", user.userName, phoneNumber)
        return success()
    }
}

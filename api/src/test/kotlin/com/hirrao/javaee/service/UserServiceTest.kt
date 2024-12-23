package com.hirrao.javaee.service

import com.hirrao.javaee.service.impl.UserServiceImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.reactive.TransactionalOperator

@SpringBootTest
@Transactional
@Rollback
class UserServiceTest(private val userService: UserServiceImpl) : StringSpec({

    extensions(SpringExtension)

    beforeTest {
        userService.register(1, "test", "test", "13574851354")
    }

    "findByUsername" {
        userService.findByUsername("test")?.userName shouldBe "test"
    }


    /*"findByUid" should {
        userService.findByUid(1)?.userName shouldBe "test"
    }

    "register" should {
        userService.register(2, "test2", "test2", "13574851355")
        userService.findByUid(2)?.userName shouldBe "test2"
    }

    "update" should {
        userService.update(1, "13574851355", "男", "2000-01-01")
        userService.findByUid(1)?.sex shouldBe "男"
    }

    "updatePassword" should {
        userService.updatePassword(1, "test2")
        userService.findByUid(1)?.userPassword shouldBe "test2"
    }

    "accountsInfo" should {}

    "modifyUserInfo" should {}

    "deleteUser" should {
        userService.deleteUser(1)
        userService.findByUid(1) shouldBe null
    }

    "searchUserByCondition" should {}

    "updateUserName" should {
        userService.updateUserName(1, "test2")
        userService.findByUid(1)?.userName shouldBe "test2"
    }

    "updateBirthday" should {
        userService.updateBirthday(1, "2000-01-01")
        userService.findByUid(1)?.birthday shouldBe "2000-01-01"
    }

    "updateSex" should {
        userService.updateSex(1, "女")
        userService.findByUid(1)?.sex shouldBe "女"
    }


     */

})
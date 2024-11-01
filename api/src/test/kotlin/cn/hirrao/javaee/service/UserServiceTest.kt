package cn.hirrao.javaee.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal open class UserServiceTest {
    @Resource
    private val userService: UserService? = null

    @BeforeEach
    fun init() {
        userService!!.register(1, "test", "test", "13574851354")
    }

    @Test
    fun findByUsername() {
        assertEquals("test", userService!!.findByUsername("test").getUserName())
    }

    @Test
    fun findByUid() {
        assertEquals("test", userService!!.findByUid(1).getUserName())
    }

    @Test
    fun register() {
        userService!!.register(2, "test2", "test2", "13574851355")
        assertEquals("test2", userService.findByUid(2).getUserName())
    }

    @Test
    fun update() {
        userService!!.update(1, "13574851355", "男", "2000-01-01")
        assertEquals("男", userService.findByUid(1).getSex())
    }

    @Test
    fun updatePassword() {
        userService!!.updatePassword(1, "test2")
        assertEquals("test2", userService.findByUid(1).getUserPassword())
    }

    @Test
    fun accountsInfo() {
    }

    @Test
    fun modifyUserInfo() {
    }

    @Test
    fun deleteUser() {
        userService!!.deleteUser(1)
        Assertions.assertNull(userService.findByUid(1))
    }

    @Test
    fun searchUserByCondition() {
    }

    @Test
    fun updateUserName() {
        userService!!.updateUserName(1, "test2")
        assertEquals("test2", userService.findByUid(1).getUserName())
    }

    @Test
    fun updateBirthday() {
        userService!!.updateBirthday(1, "2000-01-01")
        assertEquals("2000-01-01", userService.findByUid(1).getBirthday())
    }

    @Test
    fun updateSex() {
        userService!!.updateSex(1, "女")
        assertEquals("女", userService.findByUid(1).getSex())
    }
}
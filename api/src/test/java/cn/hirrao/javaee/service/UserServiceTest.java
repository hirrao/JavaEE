package cn.hirrao.javaee.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Resource
    private UserService userService;

    @BeforeEach
    void init() {
        userService.register(1, "test", "test", "13574851354");
    }

    @Test
    void findByUsername() {
        assertEquals("test", userService.findByUsername("test").getUserName());
    }

    @Test
    void findByUid() {
        assertEquals("test", userService.findByUid(1).getUserName());
    }

    @Test
    void register() {
        userService.register(2, "test2", "test2", "13574851355");
        assertEquals("test2", userService.findByUid(2).getUserName());
    }

    @Test
    void update() {
        userService.update(1, "13574851355", "男", "2000-01-01");
        assertEquals("男", userService.findByUid(1).getSex());
    }

    @Test
    void updatePassword() {
        userService.updatePassword(1, "test2");
        assertEquals("test2", userService.findByUid(1).getUserPassword());
    }

    @Test
    void accountsInfo() {
    }

    @Test
    void modifyUserInfo() {
    }

    @Test
    void deleteUser() {
        userService.deleteUser(1);
        assertNull(userService.findByUid(1));
    }

    @Test
    void searchUserByCondition() {
    }

    @Test
    void updateUserName() {
        userService.updateUserName(1, "test2");
        assertEquals("test2", userService.findByUid(1).getUserName());
    }

    @Test
    void updateBirthday() {
        userService.updateBirthday(1, "2000-01-01");
        assertEquals("2000-01-01", userService.findByUid(1).getBirthday());
    }

    @Test
    void updateSex() {
        userService.updateSex(1, "女");
        assertEquals("女", userService.findByUid(1).getSex());
    }
}
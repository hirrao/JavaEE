package com.hirrao.javaee.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.model.enums.PermissionEnum;
import com.hirrao.javaee.model.enums.SexEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisPlusTest
@ActiveProfiles("test")
@Sql("classpath:data/user.sql")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Nested
    class SelectTest {
        @Test
        public void SelectByIdExisted() {
            var user = userMapper.findById(1L);
            Assertions.assertThat(user)
                      .isNotNull()
                      .isNotNull()
                      .extracting(User::getUid, User::getUserName,
                                  User::getEmail, User::getSaltPassword,
                                  User::getSex, User::getBirthday,
                                  User::getPermission)
                      .containsExactly(2L, "test01", "test01@test.com", "test0",
                                       SexEnum.UNSET, "2004-01-02",
                                       PermissionEnum.NORMAL);
        }

        @Test
        public void SelectByIdNotExisted() {
            var user = userMapper.findById(100L);
            Assertions.assertThat(user)
                      .isNull();
        }
    }

    @Nested
    class InsertTest {
        @Test
        public void InsertNewUser() {
            userMapper.addUser(4L, "test03", "test01", "test03@test.com");
            var user = userMapper.findByUid(4L);
            Assertions.assertThat(user)
                      .isNotNull()
                      .extracting(User::getUid, User::getUserName,
                                  User::getEmail, User::getSaltPassword)
                      .containsExactly(4L, "test03", "test03@test.com",
                                       "test01");
        }
    }
}
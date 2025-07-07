package com.hirrao.javaee.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisPlusTest
@ActiveProfiles("test")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Nested
    class SelectTest {
        @Test
        public void SelectByIdExisted() {
            var user = userMapper.findById(1L);
            assert user.getUid() == 2L;
        }
    }
}

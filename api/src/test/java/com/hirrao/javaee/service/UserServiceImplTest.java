package com.hirrao.javaee.service;

import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.mapper.UserMapper;
import com.hirrao.javaee.service.impl.UserServiceV2Impl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceV2Impl userService;

    @Nested
    class CreateUser {
        public void createUserWithValidData() {
            User mockUser = new User(1L, 1L, "testUser", "test@test.com",
                                     "hashedPassword", null, null, null);
            when(userMapper.findByUid(anyLong())).thenReturn(mockUser);
        }
    }
}

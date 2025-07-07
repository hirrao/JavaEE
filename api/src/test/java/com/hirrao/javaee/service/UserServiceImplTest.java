package com.hirrao.javaee.service;

import com.hirrao.javaee.mapper.UserMapper;
import com.hirrao.javaee.service.impl.UserServiceV2Impl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceV2Impl userService;

    @Nested
    class CreateUser {

    }
}

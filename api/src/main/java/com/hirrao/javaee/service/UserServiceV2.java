package com.hirrao.javaee.service;

import com.hirrao.javaee.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceV2 {
    UserDto register(String userName, String email, String password);
}

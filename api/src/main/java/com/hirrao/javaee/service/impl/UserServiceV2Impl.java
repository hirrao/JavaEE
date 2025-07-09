package com.hirrao.javaee.service.impl;

import com.hirrao.javaee.converter.UserConverter;
import com.hirrao.javaee.exception.ClientException;
import com.hirrao.javaee.exception.ServerException;
import com.hirrao.javaee.mapper.UserMapper;
import com.hirrao.javaee.model.UserDto;
import com.hirrao.javaee.service.UserServiceV2;
import com.hirrao.javaee.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceV2Impl implements UserServiceV2 {
    private static final String ALLOWED_SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>/?~";
    private static final String PASSWORD_REGEX = "^(?!\\d+$)[a-zA-Z0-9" + Pattern.quote(
            ALLOWED_SYMBOLS) + "]{8,20}+$";
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SnowFlake snowFlake = new SnowFlake(1, 1);

    @Autowired
    UserServiceV2Impl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto register(String userName, String email, String password) {
        if (!userName.matches("^[a-zA-Z0-9_]{6,20}+$")) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "用户名必须是6-20位的字母、数字或下划线");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "密码必须包含字母、数字和特殊符号，且长度在8-20位之间");
        }
        if (!email.matches(
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ClientException(HttpStatus.UNPROCESSABLE_ENTITY,
                                      "邮箱格式不正确");
        }
        var user1 = userMapper.findByUsername(userName);
        var user2 = userMapper.findByEmail(email);
        if (user1 != null || user2 != null) {
            throw new ClientException(HttpStatus.CONFLICT, "用户名已被占用");
        }
        var saltPassword = passwordEncoder.encode(password);
        var uid = snowFlake.nextId();
        userMapper.addUser(uid, userName, saltPassword, email);
        var user = userMapper.findByUid(uid);
        if (user == null) {
            throw new ServerException("注册失败，用户信息未找到");
        }
        return UserConverter.INSTANCE.toUserDto(user);
    }
}

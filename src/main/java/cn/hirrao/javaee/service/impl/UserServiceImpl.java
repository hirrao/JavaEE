package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.mapper.UserMapper;
import cn.hirrao.javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Autowired
    private UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public User findByUid(long uid) {
        return userMapper.findByUid(uid);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void register(long uid, String userName, String userPassword, String phoneNumber) {
        //摘要
        String password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        //添加
        userMapper.addUser(uid, userName, password, phoneNumber);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}

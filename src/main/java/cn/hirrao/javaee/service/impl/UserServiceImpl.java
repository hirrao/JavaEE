package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.mapper.UserMapper;
import cn.hirrao.javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public void register(long uid,String userName, String userPassword) {
        //加密
        String p1 = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        String p2 = DigestUtils.md5DigestAsHex(p1.getBytes());
        //添加
        userMapper.addUser(uid,userName,p2);
    }
}

package com.hirrao.javaee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.mapper.UserMapper;
import com.hirrao.javaee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Deprecated
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
    @Deprecated
    public void register(long uid, String userName, String userPassword, String phoneNumber) {
        userMapper.addUser(uid, userName, userPassword, phoneNumber, "");
    }

    @Override
    @Deprecated
    public void update(long uid, String phoneNumber, String sex, String birthday) {
        userMapper.update(uid, phoneNumber, sex, birthday);
    }

    @Override
    @Deprecated
    public void updatePassword(long uid, String userPassword) {
        userMapper.updatePassword(uid, userPassword);
    }

    @Override
    @Deprecated
    public IPage<User> accountsInfo(int curPage, int size) {
        Page<User> page = new Page<>(curPage, size);
        return userMapper.selectPage(page, null);
    }

    @Override
    @Deprecated
    public void modifyUserInfo(long uid, String userName, String phoneNumber, String sex, String birthday, int permission) {
        userMapper.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission);
    }

    @Override
    public void deleteUser(long uid) {
        userMapper.deleteUser(uid);
    }

    @Override
    @Deprecated
    public IPage<User> searchUserByCondition(int curPage, int size, String searchCondition, String conditionValue) {
        Page<User> page = new Page<>(curPage, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(searchCondition, conditionValue);
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Deprecated
    public void updateUserName(long uid, String userName) {
        userMapper.updateUserName(uid, userName);
    }

    @Override
    @Deprecated
    public void updateBirthday(long uid, String birthday) {
        userMapper.updateBirthday(uid, birthday);
    }

    @Override
    @Deprecated
    public void updateSex(long uid, String sex) {
        userMapper.updateSex(uid, sex);
    }
}

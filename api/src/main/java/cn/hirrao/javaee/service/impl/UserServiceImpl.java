package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.User;
import cn.hirrao.javaee.mapper.UserMapper;
import cn.hirrao.javaee.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        userMapper.addUser(uid, userName, userPassword, phoneNumber);
    }

    @Override
    public void update(long uid, String phoneNumber, String sex, String birthday) {
        userMapper.update(uid, phoneNumber, sex, birthday);
    }

    @Override
    public void updatePassword(long uid, String userPassword) {
        userMapper.updatePassword(uid, userPassword);
    }

    @Override
    public IPage<User> accountsInfo(int curPage, int size) {
        var page = new Page<User>(curPage, size);
        return userMapper.selectPage(page, null);
    }

    @Override
    public void modifyUserInfo(long uid, String userName, String phoneNumber, String sex, String birthday, int permission) {
        userMapper.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission);
    }

    @Override
    public void deleteUser(long uid) {
        userMapper.deleteUser(uid);
    }

    @Override
    public IPage<User> searchUserByCondition(int curPage, int size, String searchCondition, String conditionValue) {
        var page = new Page<User>(curPage, size);
        var queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(searchCondition, conditionValue);
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void updateUserName(long uid, String userName) {
        userMapper.updateUserName(uid, userName);
    }

    @Override
    public void updateBirthday(long uid, String birthday) {
        userMapper.updateBirthday(uid, birthday);
    }

    @Override
    public void updateSex(long uid, String sex) {
        userMapper.updateSex(uid, sex);
    }
}

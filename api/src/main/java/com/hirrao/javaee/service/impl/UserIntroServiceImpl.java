package com.hirrao.javaee.service.impl;

import com.hirrao.javaee.entity.UserIntro;
import com.hirrao.javaee.mapper.UserIntroMapper;
import com.hirrao.javaee.service.UserIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIntroServiceImpl implements UserIntroService {
    private final UserIntroMapper userIntroMapper;

    @Autowired
    private UserIntroServiceImpl(UserIntroMapper userIntroMapper){
        this.userIntroMapper = userIntroMapper;
    }

    @Override
    public UserIntro findUserIntro(Long uid) {
        return userIntroMapper.findUserIntro(uid);
    }

    @Override
    public void updateUserIntro(Long uid, String intro) {
        userIntroMapper.updateUserIntro(uid, intro);
    }

    @Override
    public void setUserIntro(Long uid, String intro) {
        userIntroMapper.setUserIntro(uid, intro);
    }

}

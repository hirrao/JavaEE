package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.UserIntro;
import cn.hirrao.javaee.mapper.UserIntroMapper;
import cn.hirrao.javaee.service.UserIntroService;
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
        return;
    }

    @Override
    public void setUserIntro(Long uid, String intro) {
        userIntroMapper.setUserIntro(uid, intro);
        return;
    }

}

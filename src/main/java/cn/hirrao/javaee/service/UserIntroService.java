package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.UserIntro;
import org.springframework.stereotype.Service;

@Service
public interface UserIntroService {
    UserIntro findUserIntro(Long uid);
    void updateUserIntro(Long uid, String intro);
    void setUserIntro(Long uid, String intro);
}

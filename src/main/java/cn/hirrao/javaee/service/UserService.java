package cn.hirrao.javaee.service;


import cn.hirrao.javaee.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String userName);

    User findByUid(long uid);

    User findByPhoneNumber(String phoneNumber);

    void register(long uid, String userName, String userPassword, String phoneNumber);

    void update(long uid, String phoneNumber, String sex, String birthday);

    void updatePassword(long uid, String userPassword);
}

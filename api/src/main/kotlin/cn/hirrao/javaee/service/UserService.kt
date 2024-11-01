package cn.hirrao.javaee.service;


import cn.hirrao.javaee.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public interface UserService {
    User findByUsername(String userName);

    User findByUid(long uid);

    User findByPhoneNumber(String phoneNumber);

    void register(long uid, String userName, String userPassword, String phoneNumber);

    void update(long uid, String phoneNumber, String sex, String birthday);

    void updatePassword(long uid, String userPassword);

    IPage<User> accountsInfo(int curPage,int size);

    void modifyUserInfo(long uid, String userName,String phoneNumber, String sex, String birthday,int permission);

    void updateUserName(long uid, String userName);
    void updateSex(long uid, String sex);
    void updateBirthday(long uid, String birthday);

    void deleteUser(long uid);

    IPage<User> searchUserByCondition(int curPage,int size,String searchCondition,String conditionValue);
}

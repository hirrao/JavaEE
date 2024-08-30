package cn.hirrao.javaee.service;


import cn.hirrao.javaee.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String userName);
    void register(long uid,String userName, String userPassword);
}

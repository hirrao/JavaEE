package cn.hirrao.javaee.Service;


import cn.hirrao.javaee.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String userName);
    void register(long uid,String userName, String userPassword);
}

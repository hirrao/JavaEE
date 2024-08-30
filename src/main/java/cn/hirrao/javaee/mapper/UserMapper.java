package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询用户
    @Select("select * from user where userName = #{userName}")
    User findByUsername(String userName);

    @Select("select * from user where uid = #{uid}")
    User findByUid(long uid);

    @Insert("insert into user(uid,userName, userPassword) values(#{uid},#{userName}, #{userPassword})")
    void addUser(long uid, String userName, String userPassword);
}

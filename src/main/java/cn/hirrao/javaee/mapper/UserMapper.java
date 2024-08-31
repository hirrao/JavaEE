package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询用户
    @Select("select * from user where userName = #{userName}")
    User findByUsername(String userName);

    @Select("select * from user where uid = #{uid}")
    User findByUid(long uid);

    @Select("select * from user where phoneNumber = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    @Insert("insert into user(uid,userName, userPassword,phoneNumber) values(#{uid},#{userName}, #{userPassword},#{phoneNumber})")
    void addUser(long uid, String userName, String userPassword, String phoneNumber);

    @Update("update user set phoneNumber=#{phoneNumber},userPassword=#{userPassword},sex=#{sex},birthday=#{birthday} where uid=#{uid}")
    void update(User user);
}

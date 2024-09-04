package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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

    @Update("update user set phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday} where uid=#{uid}")
    void update(long uid, String phoneNumber, String sex, String birthday);

    @Update("update user set userPassword=#{userPassword} where uid = #{uid}")
    void updatePassword(long uid, String userPassword);

    @Update("update user set uid=#{uid},userName=#{userName},phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday},permission=#{permission} where uid=#{uid}")
    void modifyUserInfo(long uid, String userName,String phoneNumber, String sex, String birthday,int permission);

    @Delete("delete from user where uid=#{uid}")
    void deleteUser(long uid);
}

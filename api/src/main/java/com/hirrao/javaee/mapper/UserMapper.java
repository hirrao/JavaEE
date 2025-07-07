package com.hirrao.javaee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hirrao.javaee.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id = #{id}")
    User findById(long uid);

    @Select("select * from user where uid = #{uid}")
    User findByUid(long uid);

    @Select("select * from user where user_name = #{userName}")
    User findByUsername(String userName);

    @Select("SELECT * from user WHERE email = #{email}")
    User findByEmail(String email);

    @Insert("insert into user(uid,user_name, salt_password,email) values(#{uid},#{userName}, #{saltPassword},#{phoneNumber})")
    void addUser(long uid, String userName, String saltPassword, String email);

    @Deprecated
    @Select("select * from user where phone_number = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    @Update("update user set phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday} where uid=#{uid}")
    void update(long uid, String phoneNumber, String sex, String birthday);

    @Update("update user set salt_password=#{userPassword} where uid = #{uid}")
    void updatePassword(long uid, String userPassword);

    @Update("update user set uid=#{uid},userName=#{userName},phoneNumber=#{phoneNumber},sex=#{sex},birthday=#{birthday},permission=#{permission} where uid=#{uid}")
    void modifyUserInfo(long uid, String userName, String phoneNumber, String sex, String birthday, int permission);

    @Delete("delete from user where uid=#{uid}")
    void deleteUser(long uid);

    @Update("update user set userName=#{userName} where uid=#{uid}")
    void updateUserName(long uid, String userName);

    @Update("update user set sex=#{sex} where uid=#{uid}")
    void updateSex(long uid, String sex);

    @Update("update user set birthday=#{birthday} where uid=#{uid}")
    void updateBirthday(long uid, String birthday);
}

package com.hirrao.javaee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.model.enums.SexEnum;
import org.apache.ibatis.annotations.*;

/*
 * UserMapper.java
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /*
     * 查询部分
     */

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(long uid);

    @Select("SELECT * FROM user WHERE uid = #{uid}")
    User findByUid(long uid);

    @Select("SELECT * FROM user WHERE user_name = #{userName}")
    User findByUsername(String userName);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    // 创建新用户
    @Insert("INSERT INTO user(uid, user_name, salt_password, email) VALUES(#{uid},#{userName}, #{saltPassword},#{email})")
    void addUser(long uid, String userName, String saltPassword, String email);

    // 目前禁用, 删除用户
    @SuppressWarnings("unused")
    @Delete("DELETE FROM user WHERE uid = #{uid}")
    void deleteUser(long uid);

    /*
     * 更新部分
     */

    @Update("UPDATE user SET user_name=#{userName} WHERE uid=#{uid}")
    void updateUserName(long uid, String userName);

    @Update("UPDATE user SET email=#{email} WHERE uid=#{uid}")
    void updateEmail(long uid, String email);

    @Update("UPDATE user SET salt_password=#{saltPassword} WHERE uid=#{uid}")
    void updateSaltPassword(long uid, String saltPassword);

    @Update("UPDATE user SET sex=#{sex} WHERE uid=#{uid}")
    void updateSex(long uid, SexEnum sex);

    @Deprecated
    @Select("SELECT * FROM user WHERE phone_number = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);

    @Deprecated
    @Update("UPDATE user SET phonenumber=#{phoneNumber},sex=#{sex},birthday=#{birthday} WHERE uid=#{uid}")
    void updateD(long uid, String phoneNumber, String sex, String birthday);

    @Deprecated
    @Update("UPDATE user SET salt_password=#{userPassword} WHERE uid = #{uid}")
    void updatePasswordD(long uid, String userPassword);

    @Deprecated
    @Update("UPDATE user SET uid=#{uid},username=#{userName},phonenumber=#{phoneNumber},sex=#{sex},birthday=#{birthday},permission=#{permission} WHERE uid=#{uid}")
    void modifyUserInfoD(long uid, String userName, String phoneNumber,
                         String sex, String birthday, int permission);

    @Deprecated
    @Delete("DELETE FROM user WHERE uid=#{uid}")
    void deleteUserD(long uid);

    @Deprecated
    @Update("UPDATE user SET username=#{userName} WHERE uid=#{uid}")
    void updateUserNameD(long uid, String userName);

    @Deprecated
    @Update("UPDATE user SET sex=#{sex} WHERE uid=#{uid}")
    void updateSexD(long uid, String sex);

    @Deprecated
    @Update("UPDATE user SET birthday=#{birthday} WHERE uid=#{uid}")
    void updateBirthdayD(long uid, String birthday);
}

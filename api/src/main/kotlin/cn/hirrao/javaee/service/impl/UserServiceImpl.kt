package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.User
import cn.hirrao.javaee.mapper.UserMapper
import cn.hirrao.javaee.service.UserService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired private constructor(private val userMapper: UserMapper) : UserService {
    override fun findByUsername(userName: String?): User? {
        return userMapper.findByUsername(userName)
    }

    override fun findByUid(uid: Long): User? {
        return userMapper.findByUid(uid)
    }

    override fun findByPhoneNumber(phoneNumber: String?): User? {
        return userMapper.findByPhoneNumber(phoneNumber)
    }

    override fun register(uid: Long, userName: String?, userPassword: String?, phoneNumber: String?) {
        userMapper.addUser(uid, userName, userPassword, phoneNumber)
    }

    override fun update(uid: Long, phoneNumber: String?, sex: String?, birthday: String?) {
        userMapper.update(uid, phoneNumber, sex, birthday)
    }

    override fun updatePassword(uid: Long, userPassword: String?) {
        userMapper.updatePassword(uid, userPassword)
    }

    override fun accountsInfo(curPage: Int, size: Int): IPage<User?>? {
        val page = Page<User>(curPage.toLong(), size.toLong())
        return userMapper.selectPage(page, null)
    }

    override fun modifyUserInfo(
        uid: Long,
        userName: String?,
        phoneNumber: String?,
        sex: String?,
        birthday: String?,
        permission: Int
    ) {
        userMapper.modifyUserInfo(uid, userName, phoneNumber, sex, birthday, permission)
    }

    override fun deleteUser(uid: Long) {
        userMapper.deleteUser(uid)
    }

    override fun searchUserByCondition(
        curPage: Int,
        size: Int,
        searchCondition: String?,
        conditionValue: String?
    ): IPage<User?>? {
        val page = Page<User>(curPage.toLong(), size.toLong())
        val queryWrapper = QueryWrapper<User>()
        queryWrapper.eq(searchCondition, conditionValue)
        return userMapper.selectPage(page, queryWrapper)
    }

    override fun updateUserName(uid: Long, userName: String?) {
        userMapper.updateUserName(uid, userName)
    }

    override fun updateBirthday(uid: Long, birthday: String?) {
        userMapper.updateBirthday(uid, birthday)
    }

    override fun updateSex(uid: Long, sex: String?) {
        userMapper.updateSex(uid, sex)
    }
}

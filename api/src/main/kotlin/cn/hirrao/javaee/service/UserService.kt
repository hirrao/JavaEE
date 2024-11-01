package cn.hirrao.javaee.service

import cn.hirrao.javaee.entity.User
import com.baomidou.mybatisplus.core.metadata.IPage
import org.springframework.stereotype.Service


@Service
interface UserService {
    fun findByUsername(userName: String?): User?

    fun findByUid(uid: Long): User?

    fun findByPhoneNumber(phoneNumber: String?): User?

    fun register(uid: Long, userName: String?, userPassword: String?, phoneNumber: String?)

    fun update(uid: Long, phoneNumber: String?, sex: String?, birthday: String?)

    fun updatePassword(uid: Long, userPassword: String?)

    fun accountsInfo(curPage: Int, size: Int): IPage<User?>?

    fun modifyUserInfo(
        uid: Long,
        userName: String?,
        phoneNumber: String?,
        sex: String?,
        birthday: String?,
        permission: Int
    )

    fun updateUserName(uid: Long, userName: String?)
    fun updateSex(uid: Long, sex: String?)
    fun updateBirthday(uid: Long, birthday: String?)

    fun deleteUser(uid: Long)

    fun searchUserByCondition(curPage: Int, size: Int, searchCondition: String?, conditionValue: String?): IPage<User?>?
}

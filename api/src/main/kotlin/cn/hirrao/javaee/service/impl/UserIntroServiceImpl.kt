package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.UserIntro
import cn.hirrao.javaee.mapper.UserIntroMapper
import cn.hirrao.javaee.service.UserIntroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserIntroServiceImpl @Autowired private constructor(private val userIntroMapper: UserIntroMapper) :
    UserIntroService {
    override fun findUserIntro(uid: Long): UserIntro? {
        return userIntroMapper.findUserIntro(uid)
    }

    override fun updateUserIntro(uid: Long, intro: String?) {
        userIntroMapper.updateUserIntro(uid, intro)
    }

    override fun setUserIntro(uid: Long, intro: String?) {
        userIntroMapper.setUserIntro(uid, intro)
    }
}

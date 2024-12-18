package com.hirrao.javaee.service.impl

import com.hirrao.javaee.entity.UserIntro
import com.hirrao.javaee.mapper.UserIntroMapper
import com.hirrao.javaee.service.UserIntroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserIntroServiceImpl @Autowired private constructor(private val userIntroMapper: UserIntroMapper) :
    UserIntroService {
    override fun findUserIntro(uid: Int): UserIntro? {
        return userIntroMapper.findUserIntro(uid)
    }

    override fun updateUserIntro(uid: Long, intro: String?) {
        userIntroMapper.updateUserIntro(uid, intro)
    }

    override fun setUserIntro(uid: Int, intro: String?) {
        userIntroMapper.setUserIntro(uid, intro)
    }
}

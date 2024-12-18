package com.hirrao.javaee.service

import com.hirrao.javaee.entity.UserIntro
import org.springframework.stereotype.Service

@Service
interface UserIntroService {
    fun findUserIntro(uid: Int): UserIntro?
    fun updateUserIntro(uid: Long, intro: String?)
    fun setUserIntro(uid: Int, intro: String?)
}

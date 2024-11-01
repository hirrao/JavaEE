package cn.hirrao.javaee.utils

import cn.hirrao.javaee.entity.User

object ThreadLocalUtil {
    private val THREAD_LOCAL = ThreadLocal<User>()


    fun set(value: User) {
        THREAD_LOCAL.set(value)
    }


    fun get(): User {
        return THREAD_LOCAL.get()
    }


    fun remove() {
        THREAD_LOCAL.remove()
    }
}

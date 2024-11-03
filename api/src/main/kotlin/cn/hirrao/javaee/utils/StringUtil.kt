package cn.hirrao.javaee.utils

object StringUtil {

    fun isEmpty(s: String?): Boolean {
        return s == null || s.isEmpty()
    }
}

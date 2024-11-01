package cn.hirrao.javaee.utils;

import cn.hirrao.javaee.entity.User;

public class ThreadLocalUtil {
    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(User value) {
        THREAD_LOCAL.set(value);
    }

    public static User get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

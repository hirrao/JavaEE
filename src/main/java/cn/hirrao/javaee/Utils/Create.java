package cn.hirrao.javaee.Utils;

import cn.hirrao.javaee.Entity.User;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * 创建token
 */
public class Create {
    /**
     * 创建token
     *
     * @param user 直接传入User对象
     * @return 生成的经过压缩的token
     */
    public static String createToken(User user) {
        var builder = Jwts.builder().id(String.valueOf(user.getId())).subject(user.getUserName()).issuedAt(new Date()).signWith(CONST.key).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2));
        return builder.compact();
    }
}

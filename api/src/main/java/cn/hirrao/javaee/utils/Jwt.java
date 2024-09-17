package cn.hirrao.javaee.utils;

import cn.hirrao.javaee.entity.User;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * 创建token
 */
public class Jwt {
    /**
     * 创建token
     *
     * @param user 直接传入User对象
     * @return 生成的经过压缩的token
     */
    public static String createToken(User user) {
        var builder = Jwts.builder().id(String.valueOf(user.getUid())).subject(user.getUserName()).issuedAt(new Date()).signWith(CONST.key).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2));
        return builder.compact();
    }

    public static String parseToken(String token) {
        return Jwts.parser().verifyWith(CONST.key).build().parseSignedClaims(token).getPayload().getId();
    }
}

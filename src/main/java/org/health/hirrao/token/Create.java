package org.health.hirrao.token;

import io.jsonwebtoken.Jwts;
import org.health.hirrao.User;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * 创建token
 */
public class Create {

    final static SecretKey key = Jwts.SIG.HS256.key().build();

    /**
     * 创建token
     *
     * @param user 直接传入User对象
     * @return 生成的经过压缩的token
     */
    public static String createToken(User user) {
        var builder = Jwts.builder().id(String.valueOf(user.getId())).subject(user.getUserName()).issuedAt(new Date()).signWith(key).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2));
        return builder.compact();
    }
}

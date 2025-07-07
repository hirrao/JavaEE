package com.hirrao.javaee.utils;

import com.hirrao.javaee.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class Jwt {
    private static final String secret = "hirrao";
    private static final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    /**
     * 创建token
     *
     * @param user 直接传入User对象
     * @return 生成的经过压缩的token
     */
    public static String createToken(User user) {
        return Jwts.builder()
                   .id(UUID.randomUUID()
                           .toString())
                   .subject(String.valueOf(user.getUid()))
                   .issuedAt(new Date())
                   .signWith(key)
                   .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                   .compact();
    }

    public static String parseToken(String token) {
        return Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }
}

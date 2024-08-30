package org.health.hirrao.token;

import io.jsonwebtoken.Jwts;

import static org.health.hirrao.token.Create.key;

/**
 * 解析token
 */
public class Parse {
    /**
     * 解析token
     *
     * @param token 传入的token
     * @return 直接返回UID
     */
    public static String parseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getId();
    }
}

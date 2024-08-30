package cn.hirrao.javaee.token;

import io.jsonwebtoken.Jwts;

import static cn.hirrao.javaee.token.Create.key;

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

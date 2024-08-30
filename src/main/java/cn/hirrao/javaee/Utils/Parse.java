package cn.hirrao.javaee.Utils;

import io.jsonwebtoken.Jwts;

/**
 * 解析token
 */
public class Parse {
    /**
     * 解析token
     *
     * @param token 传入的token
     * @return 直接返回UID
     * @see cn.hirrao.javaee.Utils.Create#createToken(cn.hirrao.javaee.Entity.User)
     */
    public static String parseToken(String token) {
        return Jwts.parser().verifyWith(CONST.key).build().parseSignedClaims(token).getPayload().getId();
    }
}

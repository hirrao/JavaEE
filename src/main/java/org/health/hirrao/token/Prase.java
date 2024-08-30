package org.health.hirrao.token;

import io.jsonwebtoken.Jwts;

import static org.health.hirrao.token.Create.key;

public class Prase {
    public static String praseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getId();
    }
}

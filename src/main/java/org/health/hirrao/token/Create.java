package org.health.hirrao.token;

import io.jsonwebtoken.Jwts;
import org.health.hirrao.User;

import javax.crypto.SecretKey;
import java.util.Date;

public class Create {
    final static SecretKey key = Jwts.SIG.HS256.key().build();

    public static String createToken(User user) {
        var builder = Jwts.builder().id(user.getId()).subject(user.getName()).issuedAt(new Date()).signWith(key).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2));
        return builder.compact();
    }
}

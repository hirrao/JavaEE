package cn.hirrao.javaee.Utils;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

class CONST {
    final static SecretKey key = Jwts.SIG.HS256.key().build();
}
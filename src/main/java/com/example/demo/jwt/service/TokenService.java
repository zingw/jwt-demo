package com.example.demo.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
public class TokenService {

    private static final String commonKey = "c2VjcmV0LWtleS1qd3QtdG9rZW4=##12323213";
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(commonKey.getBytes(StandardCharsets.UTF_8));


    public static String createToken(Map<String, Object> userMap) {
        long present = new Date().getTime();
        long expireTime = 15000L;
        return Jwts.builder()
                .setClaims(userMap)
                .signWith(secretKey)
                .setIssuer("le viet duc")
                .setAudience("haha")
                .setSubject("123")
                .setIssuedAt(new Date(present))// mac dinh la HS356 neu ko chi dinh
                .setExpiration(new Date(present+expireTime))
                .compact();

    }

    public static Map<String, Object> decodeToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey)
                .build();
        return parser.parseClaimsJws(token).getBody();
    }
}

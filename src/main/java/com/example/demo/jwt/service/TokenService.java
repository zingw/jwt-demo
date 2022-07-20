package com.example.demo.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
public class TokenService {

    private static final String commonKey = "c2VjcmV0LWtleS1qd3QtdG9rZW4=##12323213";
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(commonKey.getBytes(StandardCharsets.UTF_8));


    public static String createToken(Map<String, Object> userMap) {
        long time = new Date().getTime();
        long timeExtend = time + 10000000L;
        return Jwts.builder()
                .setClaims(userMap)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(time))
                .setExpiration(new Date(timeExtend))
                .compact();
    }

    public static Map<String, Object> decodeToken(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey)
                .build();
        try{
            return parser.parseClaimsJws(token).getBody();

        }catch (ExpiredJwtException e) {
            throw new RuntimeException("ExpiredJwtException " + e.getMessage());

        }catch (UnsupportedJwtException e) {
            throw new RuntimeException("UnsupportedJwtException " + e.getMessage());

        } catch (MalformedJwtException e) {
            throw new RuntimeException("MalformedJwtException " + e.getMessage());

        } catch (SignatureException e) {
            throw new RuntimeException("SignatureException " + e.getMessage());

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("IllegalArgumentException " + e.getMessage());
        }
    }
}

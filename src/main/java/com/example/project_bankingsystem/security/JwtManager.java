package com.example.project_bankingsystem.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.project_bankingsystem.utils.exception.CustomException;
import com.example.project_bankingsystem.utils.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtManager {

    @Value("${jwt.key}")
    private String key;

    public SecretKey getSecretKey(String k) {
        System.out.println("k: " + k);
        byte[] b = Decoders.BASE64.decode(k);
        return Keys.hmacShaKeyFor(b);
    }

    public String createToken(Authentication auth) {
        System.out.println("auth: " + auth.getName());
        System.out.println("SecurityStorage.JWT_SECRETKEY: " + key);
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityStorage.JWT_EXPIRATION);

        String token = Jwts.builder()
                .subject(auth.getName())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSecretKey(key))
                .compact();

        return token;
    }

    // 토큰에서 로그인할 때 입력한 이메일 추출.
    public String getUserEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey(key))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    // 토큰 유효성 체크
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey(key)).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new CustomException("토큰만료, " + e.getMessage(), ErrorCode.TOKEN_EXPIRED);
        }
    }
}

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
        byte[] b = Decoders.BASE64.decode(k);
        return Keys.hmacShaKeyFor(b);
    }

    // accessToken만들기
    public String createAccessToken(Authentication auth) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityStorage.JWT_EXPIRATION);

        String token = Jwts.builder()
                .subject(auth.getName())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSecretKey(key))
                .compact();
        System.out.println(token);
        return token;
    }

    // refreshToken만들기
    public String createrefreshToken(Authentication auth) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityStorage.JWT_REFRESH_EXPIRATION);

        String token = Jwts.builder()
                .subject(auth.getName())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSecretKey(key))
                .compact();
        return token;
    }

    // 헤더에 토큰 전달
    // public void setTokenToHeader(HttpServletResponse response, TokenDto tokenDto)
    // {
    // // response.addHeader("Authorization", SecurityStorage.TOKEN_PREFIX +
    // // tokenDto.getAccessToken());
    // // response.sendRedirect("/checktoken");

    // // response.sendRedirect("/checktoken");
    // // response.setHeader("Authorization_Refresh", SecurityStorage.TOKEN_PREFIX +
    // // tokenDto.getRefreshToken());
    // // HttpHeaders headers = new HttpHeaders();
    // // headers.setContentType(MediaType.APPLICATION_JSON);
    // // headers.set("Authorization_Access", tokenDto.getAccessToken());
    // // headers.add("Authorization_Refresh", tokenDto.getRefreshToken());
    // System.out.println("setTokenToHeader 완료");
    // }

    // 로그인할 때 입력한 이메일 추출(토큰에서 추출).
    public String getUserEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey(key))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        // System.out.println("Claims: " + claims.getSubject()); -> ok
        return claims.getSubject();
    }

    // 토큰 유효성 체크
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey(key)).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new CustomException("validateToken -> TOKEN ERROR, " + e.getMessage(), ErrorCode.TOKEN_EXPIRED);
        }
    }
}

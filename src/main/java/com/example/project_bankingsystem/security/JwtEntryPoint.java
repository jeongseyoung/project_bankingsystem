package com.example.project_bankingsystem.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    /*
     * 메인서버가 작동하기 전에 토큰인증처리를 해야하므로,
     * 따로 exception처리
     * 
     * 헤더에 토큰이 담겨있지 않거나, 토큰이 만료 또는 조작된 경우 토큰을 검증하는 부분에서 프로세스가 끝나게 되므로,
     * 이를 잡아내기 위해서는 SpringSecurity에서 제공하는 AuthenticationEntryPoint를 상속받아 재정의 해야했다.
     * 예외가 발생한 경우 /exception/entrypoint로 포워딩하도록 처리
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}

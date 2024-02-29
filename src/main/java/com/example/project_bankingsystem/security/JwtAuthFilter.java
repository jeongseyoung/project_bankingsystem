package com.example.project_bankingsystem.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // header에서 토큰 가져옴
        String token = getTokenFromHeader(request);

        // 토큰 유효성 체크 후 로그인!
        if (StringUtils.hasText(token) && jwtManager.validateToken(token)) {
            UserDetails user = userDetailsService.loadUserByUsername(jwtManager.getUserEmailFromToken(token));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    // 토큰bearer제거
    public String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

            return Optional.ofNullable(bearerToken).filter(f -> jwtManager.validateToken(f))
                    .map(m -> m.substring(7, m.length())).get();

            // return bearerToken.substring(7, bearerToken.length());
        }
        return null;
        // throw new CustomException("잘못된 토큰", ErrorCode.TOKEN_ERROR);
    }

}

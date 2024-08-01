package com.example.project_bankingsystem.service;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_bankingsystem.dto.TokenDto;
import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.RefreshTokenEntity;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.RefreshTokenRepository;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.security.JwtManager;
import com.example.project_bankingsystem.utils.exception.CustomException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = {
            Exception.class, CustomException.class
    })
    public TokenDto login(UserDto userDto, HttpServletResponse response) throws IOException {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        // 인증객체(Authentication) 저장 -> 어디서든 꺼낼 수 있음.
        SecurityContextHolder.getContext().setAuthentication(auth);
        // access, refresh 토큰 만들고 리턴.
        TokenDto tokenDto = new TokenDto(jwtManager.createAccessToken(auth), jwtManager.createrefreshToken(auth));
        System.out.println(tokenDto);
        UserEntity userEntity = userRepository.findByemail(auth.getName()).get();
        RefreshTokenEntity refreshTokenEntity = mapToRefreshTokenEntity(tokenDto.getRefreshToken(), userEntity);
        userEntity.setRefreshTokenEntity(refreshTokenEntity);

        refreshTokenRepository.save(refreshTokenEntity);
        userRepository.save(userEntity);
        return tokenDto;
    }

    public RefreshTokenEntity mapToRefreshTokenEntity(String refreshToken, UserEntity userEntity) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                .refreshToken(refreshToken)
                .userEntity(userEntity)
                .build();
        return refreshTokenEntity;
    }
}

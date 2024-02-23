package com.example.project_bankingsystem.service;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public void login(UserDto userDto) {
        System.out.println("userDto(service): " + userDto.getPassword());

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        // 인증객체(Authentication) 저장 -> 어디서든 꺼낼 수 있음.
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}

package com.example.project_bankingsystem.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_bankingsystem.dto.TokenDto;
import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.security.JwtManager;
import com.example.project_bankingsystem.security.SecurityStorage;
import com.example.project_bankingsystem.service.AuthService;
import com.example.project_bankingsystem.service.MyAccountService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final MyAccountService accountService;
    private final AuthService authService;
    private final JwtManager jwtManager;
    private final UserRepository userRepository;
    private TokenDto tokenDto;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response)
            throws IOException {
        System.out.println("aaa" + userDto.getId() + " " + userDto.getEmail());
        tokenDto = authService.login(userDto, response);
        System.out.println("token " + tokenDto.getAccessToken());
        // // HttpHeaders httpHeaders = new HttpHeaders();
        response.sendRedirect("/myhome");

        System.out.println("tokenDto: " + tokenDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myhome")
    public ModelAndView setheadertoken(HttpServletResponse response) throws IOException {

        response.addHeader("Authorization", SecurityStorage.TOKEN_PREFIX +
                tokenDto.getAccessToken());
        response.addHeader("Authorization_refresh", SecurityStorage.TOKEN_PREFIX +
                tokenDto.getRefreshToken());
        // response.sendRedirect("/currentinfo"); // 이거 하면 토큰 저장안됨
        String email = jwtManager.getUserEmailFromToken(tokenDto.getAccessToken());

        return new ModelAndView("myhome", "list",
                accountService.myaccount(mapToUserDto(userRepository.findByemail(email).get()))); // -> accountDto가 필요함
    }

    public UserDto mapToUserDto(UserEntity userEntity) {
        UserDto setUserDto = UserDto.builder()
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .build();
        return setUserDto;
    }
}

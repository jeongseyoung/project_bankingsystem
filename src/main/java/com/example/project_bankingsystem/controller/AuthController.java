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
import com.example.project_bankingsystem.security.SecurityStorage;
import com.example.project_bankingsystem.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private TokenDto tokenDto;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response)
            throws IOException {
        tokenDto = authService.login(userDto, response);

        // HttpHeaders httpHeaders = new HttpHeaders();
        response.sendRedirect("/setheadertoken");

        System.out.println("tokenDto: " + tokenDto);
        // return new ModelAndView("checktoken");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/setheadertoken")
    public ResponseEntity<?> test(HttpServletResponse response) throws IOException {

        response.addHeader("Authorization", SecurityStorage.TOKEN_PREFIX +
                tokenDto.getAccessToken());
        response.addHeader("Authorization_refresh", SecurityStorage.TOKEN_PREFIX +
                tokenDto.getRefreshToken());
        // response.sendRedirect("/currentinfo"); // 이거 하면 토큰 저장안됨
        System.out.println("mario3");
        return new ResponseEntity<>(HttpStatus.OK);
        // return new ModelAndView("currentinfo", HttpStatus.OK);
        // return "redirect:/currentinfo";

    }

    @GetMapping("/currentinfo") // 인터넷창 주소
    public ModelAndView currentinfo() {
        System.out.println("currentinfo: ");
        return new ModelAndView("currentinfo"); // 파일명
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

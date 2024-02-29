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
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto, HttpServletResponse response)
            throws IOException {
        tokenDto = authService.login(userDto, response);
        // HttpHeaders httpHeaders = new HttpHeaders();
        response.sendRedirect("/checktoken");

        System.out.println("tokenDto: " + tokenDto);
        // return new ModelAndView("checktoken");
        return new ResponseEntity<TokenDto>(tokenDto, HttpStatus.OK);
    }

    @GetMapping("/checktoken")
    public ModelAndView test(HttpServletResponse response) {

        response.addHeader("Authorization", SecurityStorage.TOKEN_PREFIX +
                tokenDto.getAccessToken());

        return new ModelAndView("checktoken");

    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

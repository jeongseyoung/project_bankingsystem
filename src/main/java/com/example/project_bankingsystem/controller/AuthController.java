package com.example.project_bankingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(UserDto userDto) {
        authService.login(userDto);
        return "success";
    }

}

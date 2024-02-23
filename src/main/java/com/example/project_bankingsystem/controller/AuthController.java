package com.example.project_bankingsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.project_bankingsystem.dto.TokenDto;
import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(UserDto userDto) {
        return new ResponseEntity<TokenDto>(authService.login(userDto), HttpStatus.OK);
    }
}

package com.example.project_bankingsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.service.MyAccountService;

import lombok.RequiredArgsConstructor;

/**
 * MyAccountController
 */
@Controller
@RequiredArgsConstructor
public class MyAccountController {

    private final MyAccountService myAccountService;

    // 내 통장 정보 조회
    @PostMapping("/myaccount")
    public ResponseEntity<UserDto> myaccount(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(myAccountService.myaccount(userDto), HttpStatus.OK);
    }

}

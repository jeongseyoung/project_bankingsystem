package com.example.project_bankingsystem.controller;

import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.BankMainEntity;
import com.example.project_bankingsystem.service.SignUpService;

import lombok.RequiredArgsConstructor;

/**
 * signUpController
 */
// @Log4j2
// @Slf4j
@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String sb() {
        System.out.println("sbsbsbsbsb");
        return "test";
    }

    @GetMapping("/test")
    public void test(String username) {
        System.out.println("username: " + username);

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        return new ResponseEntity<UserDto>(signUpService.signup(userDto), HttpStatus.OK);
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<?> signupAdmin(@RequestBody BankMainEntity bankMainEntity) {
        return ResponseEntity.ok().body(signUpService.signupAdmin(bankMainEntity));
    }
}
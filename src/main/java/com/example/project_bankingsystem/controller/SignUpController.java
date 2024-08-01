package com.example.project_bankingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.BankMainEntity;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.service.SignUpService;
import com.example.project_bankingsystem.utils.exception.CustomException;
import com.example.project_bankingsystem.utils.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * signUpController
 */

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;
    private final UserRepository userRepository;

    // @PreAuthorize
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    // @ModelAttribute
    @PostMapping(value = "/signup")
    public ModelAndView signup(UserDto userDto) {
        System.out.println("controller signup: ");
        if (userDto == null || userRepository.existsByemail(userDto.getEmail())) {
            throw new CustomException("이메일을 다시 입력하세요.", ErrorCode.EMAIL_DUPLICATE);
        }
        return new ModelAndView("myinfo", "userDto", signUpService.signup(userDto));
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<?> signupAdmin(@RequestBody BankMainEntity bankMainEntity) {
        return ResponseEntity.ok().body(signUpService.signupAdmin(bankMainEntity));
    }
}
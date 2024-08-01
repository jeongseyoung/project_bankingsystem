package com.example.project_bankingsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_bankingsystem.dto.BankAccountDto;
import com.example.project_bankingsystem.dto.TokenDto;
import com.example.project_bankingsystem.security.JwtManager;
import com.example.project_bankingsystem.service.Depo_Withdrawal_Service;
import lombok.RequiredArgsConstructor;

/**
 * Depo_Withdrawal_Controller
 */
@RestController
@RequiredArgsConstructor
public class Depo_Withdrawal_Controller {

    private final Depo_Withdrawal_Service depo_withdrawal_Service;
    private final JwtManager jwtManager;

    // 이체, 송금
    @PostMapping("/transfer") // ResponseEntity<BankAccountDto>
    public ModelAndView transfer(@RequestBody BankAccountDto bankAccountDto) {
        String token = bankAccountDto.getAccessToken().substring(7, bankAccountDto.getAccessToken().length());
        if (jwtManager.validateToken(token)) {
            bankAccountDto.setEmail(jwtManager.getUserEmailFromToken(token));
            return new ModelAndView("myhome", "list",
                    depo_withdrawal_Service.transfer(bankAccountDto)); // -> accountDto가 필요함
            // return new
            // ResponseEntity<BankAccountDto>(depo_withdrawal_Service.transfer(bankAccountDto),
            // HttpStatus.OK);
        }
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return null;
    }

    // 입금
    @PostMapping("/deposit")
    public ResponseEntity<BankAccountDto> deposit(@RequestBody BankAccountDto bankAccountDto) {
        return new ResponseEntity<BankAccountDto>(depo_withdrawal_Service.deposit(bankAccountDto),
                HttpStatus.OK);
    }

    // 출금
    @PostMapping("/withdrawal")
    public ResponseEntity<BankAccountDto> withdrawal(@RequestBody BankAccountDto bankAccountDto) {
        //System.out.println("bankAccountDto: " + bankAccountDto.getMyaccount());
        return new ResponseEntity<BankAccountDto>(depo_withdrawal_Service.withdrawal(bankAccountDto),
                HttpStatus.OK);
    }

    @GetMapping("/depotrans")
    public ModelAndView depotrans() {
        //System.out.println("depotrans");

        return new ModelAndView("depotrans");
    }

    @PostMapping("/tokentest") // @RequestBody String token
    public String tokentest(@RequestBody TokenDto tokenDto) {
        //System.out.println("TOKEN TESTTTTTTTTT");
        //System.out.println("tokenDto: " + tokenDto.getAccessToken());

        if (jwtManager.validateToken(tokenDto.getAccessToken().substring(7, tokenDto.getAccessToken().length()))) {
            System.out.println("TRUE");
        }
        // System.out.println("request.getHeader " + request.getHeader("token"));
        // System.out.println("tkn: " + tkn);
        return null;
        // return tkn;
    }
}

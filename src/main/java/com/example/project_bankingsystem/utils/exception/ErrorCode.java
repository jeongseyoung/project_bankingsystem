package com.example.project_bankingsystem.utils.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    BANKACCOUNT_NOT_FOUND("없는 계좌입니다.", HttpStatus.NOT_FOUND),
    TRANSFER_FAILED("이체 실패", HttpStatus.NOT_IMPLEMENTED),
    PASSWORD_INCORRECT("비밀번호가 일치하지 않음", HttpStatus.UNAUTHORIZED),
    INSUFFICENT_BALANCE("잔고 부족", HttpStatus.INSUFFICIENT_STORAGE),
    EMAIL_DUPLICATE("이미 존재하는 EMAIL입니다", HttpStatus.FORBIDDEN),
    EMAIL_NOT_FOUND("없는 EMAIL입니다.", HttpStatus.NOT_FOUND),
    TOKEN_EXPIRED("토큰 만료", HttpStatus.NOT_ACCEPTABLE),
    TOKEN_ERROR("잘못된 토큰", HttpStatus.FORBIDDEN);

    private String message;
    private HttpStatus httpStatus;
}

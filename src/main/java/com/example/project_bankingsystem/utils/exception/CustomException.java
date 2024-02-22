package com.example.project_bankingsystem.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private String message;
    private ErrorCode errorCode;
}

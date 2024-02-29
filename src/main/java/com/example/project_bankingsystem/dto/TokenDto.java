package com.example.project_bankingsystem.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

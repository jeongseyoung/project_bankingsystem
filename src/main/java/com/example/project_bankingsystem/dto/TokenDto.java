package com.example.project_bankingsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer ";

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

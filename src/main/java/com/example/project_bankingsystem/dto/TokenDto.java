package com.example.project_bankingsystem.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}

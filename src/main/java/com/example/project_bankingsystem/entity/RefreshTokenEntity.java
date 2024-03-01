package com.example.project_bankingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenEntity {
    @Id
    @GeneratedValue
    private int id;

    private String refreshToken;

    @OneToOne(mappedBy = "refreshTokenEntity")
    private UserEntity userEntity;

    void addUserEntity(String refreshToken, UserEntity userEntity) {
        this.refreshToken = refreshToken;
        this.userEntity = userEntity;
    }
}

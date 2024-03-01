package com.example.project_bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_bankingsystem.entity.RefreshTokenEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Integer> {

}

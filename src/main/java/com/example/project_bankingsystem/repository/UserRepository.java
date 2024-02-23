package com.example.project_bankingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_bankingsystem.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByaccount(String account);

    Optional<UserEntity> findByemail(String email);

    boolean existsByemail(String email);
}

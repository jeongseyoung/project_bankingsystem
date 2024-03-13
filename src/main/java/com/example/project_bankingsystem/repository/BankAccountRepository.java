package com.example.project_bankingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_bankingsystem.entity.BankAccountEntity;
import com.example.project_bankingsystem.entity.UserEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {

    Optional<BankAccountEntity> findByaccount(String account);

    // Optional<BankAccountEntity> findByuser_id(int id);

    Optional<BankAccountEntity> findByuserEntity(UserEntity userEntity);

}

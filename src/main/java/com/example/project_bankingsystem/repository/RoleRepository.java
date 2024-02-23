package com.example.project_bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_bankingsystem.entity.RolesEntity;

public interface RoleRepository extends JpaRepository<RolesEntity, Integer> {

}

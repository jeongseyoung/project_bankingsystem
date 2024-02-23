package com.example.project_bankingsystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RolesEntity {
    @Id
    @GeneratedValue
    private int id;
    private String role;

    @OneToMany(mappedBy = "rolesEntity", cascade = CascadeType.ALL)
    private List<UserEntity> userEntity = new ArrayList<UserEntity>();

    public void addUserEntity(UserEntity userEntity) {
        userEntity.setRolesEntity(this);
        this.userEntity.add(userEntity);
    }
}

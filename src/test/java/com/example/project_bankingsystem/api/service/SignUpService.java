package com.example.project_bankingsystem.api.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.RolesEntity;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.RoleRepository;
import com.example.project_bankingsystem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class SignUpService {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private com.example.project_bankingsystem.service.SignUpService signUpService;

    @Test
    public void signup_returnUserDto() throws Exception {

        // RolesEntity role = roleRepository.findById(2).get();
        // System.out.println("role: " + role);
        RolesEntity role = new RolesEntity();
        role.setId(2);
        role.setRole("USER");
        UserEntity userEntity = UserEntity.builder().name("aff").email("aff@aff.com").password("aff")
                .phone("010-0000-0000").rolesEntity(role)
                .build();
        UserDto userDto = UserDto.builder().name("ccc").email("ccc@ccc.com").password("ccc").phone("010-0000-0000")
                .role(userEntity.getRolesEntity().getRole()).build();
        System.out.println("userDto: " + userDto);
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        UserDto savedUserDto = signUpService.signup(userDto);
        Assertions.assertThat(savedUserDto).isNotNull();

    }

}

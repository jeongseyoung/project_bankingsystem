package com.example.project_bankingsystem.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.RoleRepository;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.utils.exception.CustomException;
import com.example.project_bankingsystem.utils.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

/*
 * 비번체크, 역할(Role)저장
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByemail(email).orElseThrow(() -> {
            throw new CustomException("없는 email입니다.", ErrorCode.EMAIL_NOT_FOUND);
        });
        // role 각각을 grantedautority에 추가
        // List<RolesEntity> rolesEntity = roleRepository.findAll();
        Collection<GrantedAuthority> grantedAuthority = roleRepository.findAll().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());

        return new User(userEntity.getEmail(), userEntity.getPassword(), grantedAuthority);
    }

}

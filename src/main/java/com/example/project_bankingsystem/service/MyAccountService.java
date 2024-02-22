package com.example.project_bankingsystem.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.BankAccountEntity;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.BankAccountRepository;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.utils.exception.CustomException;
import com.example.project_bankingsystem.utils.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    /*
     * 입출금내역 만들기~
     */
    public UserDto myaccount(UserDto userDto) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        UserEntity userEntity = userRepository.findByaccount(userDto.getAccount()).orElseThrow(() -> {
            throw new CustomException("없는 유저입니다.", ErrorCode.BANKACCOUNT_NOT_FOUND);
        });
        if (checkPw(userDto.getPassword(), userEntity.getPassword())) {
            // 이거 다시.
            bankAccountEntity = bankAccountRepository.findByuserEntity(userEntity)
                    .orElseThrow(() -> {
                        throw new CustomException("없는 계좌입니다.", ErrorCode.BANKACCOUNT_NOT_FOUND);
                    });
        } else {
            throw new CustomException("비번이 일치하지 않음", ErrorCode.PASSWORD_INCORRECT);
        }
        return mapToUserDto(userEntity, bankAccountEntity);
    }

    public UserDto mapToUserDto(UserEntity userEntity, BankAccountEntity bankAccountEntity) {
        UserDto setUserDto = UserDto.builder()
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .email(userEntity.getEmail())
                .account(bankAccountEntity.getAccount())
                .balance(bankAccountEntity.getBalance())
                .build();
        return setUserDto;
    }

    public boolean checkPw(String pw, String hashedPw) {
        return BCrypt.checkpw(pw, hashedPw);
    }

}

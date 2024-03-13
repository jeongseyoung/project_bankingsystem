package com.example.project_bankingsystem.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.project_bankingsystem.dto.BankAccountDto;
import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.BankAccountEntity;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.BankAccountRepository;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.security.JwtManager;
import com.example.project_bankingsystem.utils.exception.CustomException;
import com.example.project_bankingsystem.utils.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyAccountService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private JwtManager jwtManager;

    /*
     * 입출금내역 만들기~
     */
    public UserDto myaccount(UserDto userDto) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        UserEntity userEntity = userRepository.findByemail(userDto.getEmail()).orElseThrow(() -> {
            throw new CustomException("없는 유저입니다.", ErrorCode.BANKACCOUNT_NOT_FOUND);
        });

        System.out.println(userEntity.getPassword() + " " + userDto.getPassword()); // userDto.getPassword() -> null
        // 이거 다시.
        bankAccountEntity = bankAccountRepository.findByaccount(userEntity.getAccount())
                .orElseThrow(() -> {
                    throw new CustomException("없는 계좌입니다.", ErrorCode.BANKACCOUNT_NOT_FOUND);
                });

        return mapToUserDto(userEntity, bankAccountEntity);
    }

    // 입출금내역 리스트
    public BankAccountDto getMyAccount(String accessToken) {
        return mapToBankAccountDto(bankAccountRepository
                .findByuserEntity(userRepository.findByemail(jwtManager.getUserEmailFromToken(accessToken)).get())
                .get());
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

    public BankAccountDto mapToBankAccountDto(BankAccountEntity bankAccountEntity) {
        BankAccountDto bankAccountDto = BankAccountDto.builder()
                .myaccount(bankAccountEntity.getAccount())
                .transferFee(bankAccountEntity.getTransfer())
                .depositFee(bankAccountEntity.getDeposit())
                .withdrawalFee(bankAccountEntity.getWithdrawal())
                .build();
        return bankAccountDto;
    }

    public boolean checkPw(String pw, String hashedPw) {
        return BCrypt.checkpw(pw, hashedPw);
    }

}

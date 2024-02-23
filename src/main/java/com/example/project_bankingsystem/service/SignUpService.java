package com.example.project_bankingsystem.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.BankAccountEntity;
import com.example.project_bankingsystem.entity.BankMainEntity;
import com.example.project_bankingsystem.entity.RolesEntity;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.BankAccountRepository;
import com.example.project_bankingsystem.repository.BankMainRepository;
import com.example.project_bankingsystem.repository.RoleRepository;
import com.example.project_bankingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * signUpService
 */
@Service
@RequiredArgsConstructor
public class SignUpService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final BankMainRepository bankMainRepository;
    private final RoleRepository roleRepository;

    /*
     * User생성(가입)과 계좌개설.
     * 
     * CheckedException, UnCheckedException 발생시 롤백.
     * 트랜잭션은 RuntimeException(unchecked exception)이 발생하면
     * 롤백 디폴트값으로 설정되어 있음.
     */
    @Transactional(rollbackFor = { Exception.class })
    public UserDto signup(UserDto userDto) {
        String pw = encodePassword(userDto.getPassword()); // 비밀번호 Bcrypt로 암호화
        String account = createAccount();
        UserEntity userEntity = mapToUserEntity(userDto, pw);
        BankAccountEntity bankAccountEntity = mapToBankAccountEntity(userEntity, pw);
        // 계좌가 중복이면 다시 생성
        if (!checkAccount(account)) {
            userEntity.setAccount(account);
            bankAccountEntity.setAccount(account);
        } else {
            account = createAccount(); // 이거도 다시 중복 체크해야되는데
            userEntity.setAccount(account);
            bankAccountEntity.setAccount(account);
        }
        // 은행에서 모든계좌, 모든유저를 관리해야하므로.
        BankMainEntity bankMainEntity = bankMainRepository.findById(52).get();
        bankMainEntity.addBankAccountEntity(bankAccountEntity);
        bankMainEntity.addUserEntity(userEntity);

        bankAccountRepository.save(bankAccountEntity);
        bankMainRepository.save(bankMainEntity);

        return mapToUserDto(userRepository.save(userEntity));
    }

    /*
     * 계좌생성,
     * 000-00-00000000 각 부분을 랜덤함수를 이용하여 생성 후 합침
     */
    public String createAccount() {
        int phase1 = (int) (Math.random() * (999 - 100)) + 100;
        int phase2 = (int) (Math.random() * (99 - 10)) + 10;
        int phase3 = (int) (Math.random() * (99999999 - 10000000)) + 10000000;

        String account = phase1 + "-" + phase2 + "-" + phase3;

        return account;
    }

    /*
     * 계좌중복 체크
     * true - 중복, false - 생성가능
     */
    public boolean checkAccount(String account) {
        return bankAccountRepository.findByaccount(account).isPresent();
    }

    /*
     * Dto -> Entity
     */
    public UserEntity mapToUserEntity(UserDto userDto, String pw) {
        RolesEntity role = roleRepository.findById(2).get();
        UserEntity userEntity = UserEntity.builder()
                .name(userDto.getName())
                .password(pw)
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .rolesEntity(role)
                .build();
        return userEntity;
    }

    /*
     * Entity -> Dto
     */
    public UserDto mapToUserDto(UserEntity userEntity) {
        UserDto userDto = UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .account(userEntity.getAccount())
                .role(userEntity.getRolesEntity().getRole())
                // .balance(userEntity.getBalance())
                .signUpDate(userEntity.getSignUpDate())
                .build();
        return userDto;
    }

    public BankAccountEntity mapToBankAccountEntity(UserEntity userEntity, String pw) {
        BankAccountEntity bankAccountEntity = BankAccountEntity.builder()
                .password(pw)
                .deposit(0)
                .withdrawal(0)
                .balance(0)
                .userEntity(userEntity)
                .build();
        return bankAccountEntity;
    }

    // 비밀번호 암호화
    public String encodePassword(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt());
    }

    /*
     * 관리자 생성
     */
    public BankMainEntity signupAdmin(BankMainEntity bankMainEntity) {
        BankMainEntity Admin = BankMainEntity.builder()
                .admin("admin")
                .password(encodePassword("admin"))
                .build();
        bankMainRepository.save(Admin);
        return Admin;
    }
}
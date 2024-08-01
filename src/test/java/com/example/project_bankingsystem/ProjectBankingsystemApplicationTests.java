package com.example.project_bankingsystem;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project_bankingsystem.api.service.SignUpService;
import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.UserRepository;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProjectBankingsystemApplicationTests {
	// @Mock
	// private UserRepository userRepository;

	// @InjectMocks
	// private SignUpService signUpService;

	@Test
	public void contextLoads() {
		// UserEntity userEntity =
		// UserEntity.builder().name("aff").email("aff@aff.com").password("aff")
		// .phone("010-0000-0000").build();
		// UserDto userDto =
		// UserDto.builder().name("ccc").email("ccc@ccc.com").password("ccc").phone("010-0000-0000")
		// .role("USER").build();

		// when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
		// UserDto savedUser = signUpService.signup(userDto);
		// Assertions.assertThat(savedUser).isNotNull();

	}

}

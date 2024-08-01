package com.example.project_bankingsystem.api.controller;

import static org.mockito.BDDMockito.given;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.example.project_bankingsystem.dto.UserDto;
import com.example.project_bankingsystem.entity.UserEntity;
import com.example.project_bankingsystem.repository.UserRepository;
import com.example.project_bankingsystem.service.SignUpService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = SignUpController.class)
@AutoConfigureMockMvc
@EnableWebSecurity
@ExtendWith(MockitoExtension.class)
public class SignUpController {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;
        @MockBean
        private SignUpService signUpService;
        @MockBean
        private UserRepository userRepository;

        private UserEntity userEntity;
        private UserDto userDto;

        @BeforeEach
        public void init() {
                userEntity = UserEntity.builder().name("aff").email("aff@aff.com").password("aff")
                                .phone("010-0000-0000")
                                .build();
                userDto = UserDto.builder().name("ccc").email("ccc@ccc.com").password("ccc").phone("010-0000-0000")
                                .role("USER").build();
        }

        @Test
        public void singupTest_returnUserDto() throws Exception {
                given(signUpService.signup(ArgumentMatchers.any())).willAnswer(i -> i.getArgument(0));

                ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/signup")// .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto)));
                resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.account",
                                                CoreMatchers.is(userDto.getAccount())))
                                // .andDo(print());
                                .andDo(MockMvcResultHandlers.print());
        }
}

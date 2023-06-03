package com.example.tableing.controller;

import com.example.tableing.model.Auth;
import com.example.tableing.model.Member;
import com.example.tableing.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @MockBean
    private MemberService memberService;

    private PasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signup() throws Exception {
        Auth.SignUp signUp = new Auth.SignUp();
        signUp.setUsername("test");
        signUp.setPassword("1111");
        signUp.setRole("USER");

        given(memberService.register(signUp))
                .willReturn(Member.builder()
                        .id(1L)
                        .username("test")
                        .role("USER")
                        .password(passwordEncoder.encode("1111"))
                        .build());
        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    void signin() {
    }
}
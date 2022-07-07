package com.gdscewha.ewhaclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscewha.ewhaclub.dto.CheckEmailDto;
import com.gdscewha.ewhaclub.dto.CheckNameDto;
import com.gdscewha.ewhaclub.dto.LoginInfoDto;
import com.gdscewha.ewhaclub.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void before(WebApplicationContext wac) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @DisplayName("회원가입_O")
    @Test
    void join_o() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup").contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new UserDto(
                                "hehehehehehehehehehe@ewhain.net",
                                "hehehehehehehehehe",
                                "헤헤헤헤헤헤헤"
                        ))
                ))
        .andExpect(status().isOk());
    }

    @DisplayName("닉네임_중복검사_중복O")
    @Test
    void check_name_o() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup/checkName").contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new CheckNameDto(
                                        "망충망충"
                                )
                        )))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"already existed nickname\"}"));

    }

    @DisplayName("이메일_중복검사_중복O")
    @Test
    void check_email_o() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup/checkEmail").contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new CheckEmailDto(
                                "sujin@ewhain.net"
                                )
                        )))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\":\"already existed email\"}"));

    }

    @DisplayName("로그인_O")
    @Test
    void signin_o() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signin").contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new LoginInfoDto(
                                "sujin@ewhain.net",
                                "sujinpassword"
                                )
                        )))
                .andExpect(status().isOk());

    }
}

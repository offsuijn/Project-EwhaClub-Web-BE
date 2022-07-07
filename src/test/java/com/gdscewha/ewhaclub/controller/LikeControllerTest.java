package com.gdscewha.ewhaclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdscewha.ewhaclub.domain.User;
import com.gdscewha.ewhaclub.dto.LoginInfoDto;
import com.gdscewha.ewhaclub.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.security.config.BeanIds;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class LikeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    String getAccessToken() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users/signin").contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(new LoginInfoDto(
                                        "sujinsujin@ewhain.net",
                                        "ewhaclub"
                                )
                        )))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int length = content.length();
        String token = content.substring(12, length-2);
        return token;
    }

    @BeforeEach
    void before(WebApplicationContext wac) throws ServletException {
        DelegatingFilterProxy delegateProxyFilter = new DelegatingFilterProxy();
        delegateProxyFilter.init(
                new MockFilterConfig(wac.getServletContext(), BeanIds.SPRING_SECURITY_FILTER_CHAIN));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(delegateProxyFilter)
                .alwaysDo(print())
                .build();

    }

    @DisplayName("좋아요_O")
    @Test
    void like() throws Exception{
        mockMvc.perform(post("/like/1").header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()))
                .andExpect(content().string("SUCCESS ADD LIKE"));
    }

    @DisplayName("좋아요_취소_O")
    @Test
    void unlike() throws Exception {
        mockMvc.perform(delete("/like/6").header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()))
                .andExpect(content().string("SUCCESS DELETE LIKE"));
    }

    @DisplayName("좋아요_동아리_조회_O")
    @Test
    void getLikes() throws Exception {
        mockMvc.perform(get("/like").header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()))
                .andExpect(status().isOk());

    }
}
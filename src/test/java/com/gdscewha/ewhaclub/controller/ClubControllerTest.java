package com.gdscewha.ewhaclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class ClubControllerTest {

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

    @DisplayName("동아리_전체조회_O")
    @Test
    void getClubs_O() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/clubs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("이클레스"))
                .andExpect(status().isOk());

    }

    @DisplayName("동아리_상세조회_O")
    @Test
    void getDetailPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/clubs/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("릴리즈"))
                .andExpect(status().isOk());

    }

    @DisplayName("동아리_카테고리별_조회_O")
    @Test
    void getCategory() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/category/7").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("이화체인"))
                .andExpect(status().isOk());
    }

    @DisplayName("동아리_검색_O")
    @Test
    void searchClubs() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/clubs/search?name=po").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("폴라리스"))
                .andExpect(status().isOk());
    }
}
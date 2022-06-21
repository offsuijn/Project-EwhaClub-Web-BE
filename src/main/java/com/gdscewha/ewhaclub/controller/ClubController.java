package com.gdscewha.ewhaclub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gdscewha.ewhaclub.domain.Club;
import com.gdscewha.ewhaclub.dto.DetailPageClubDto;
import com.gdscewha.ewhaclub.dto.MainPageClubDto;
import com.gdscewha.ewhaclub.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubController {
    @GetMapping("/")
    public String index() {
        return "Hello, this is a main page";
    }


    @Autowired
    private ClubService clubService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ClubController(ClubService clubService){
        this.clubService = clubService;
        this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @GetMapping(value="/clubs")
    public List<MainPageClubDto> getClubs() {
        return clubService.findClubs();
    }

    @GetMapping(value="/clubs/{clubId}")
    public DetailPageClubDto getDetailPage(@PathVariable("clubId") Long clubId) throws Exception {
        DetailPageClubDto detailPageClubDto = clubService.getDetailPage(clubId);
        return objectMapper.readValue(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(detailPageClubDto), DetailPageClubDto.class);
    }

    @GetMapping(value="/category/{category}")
    public List<MainPageClubDto> getCategory(@PathVariable("category") int category) throws Exception {
        return clubService.getCategory(category);
    }

    @GetMapping(value="/clubs/search")
    public List<MainPageClubDto> searchClubs(@RequestParam("name") String name){
        return clubService.searchClubs(name);
    }
}

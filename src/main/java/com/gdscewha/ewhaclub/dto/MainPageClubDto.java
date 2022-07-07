package com.gdscewha.ewhaclub.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gdscewha.ewhaclub.domain.Club;
import com.gdscewha.ewhaclub.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MainPageClubDto {

    @NotNull
    private Long id;
    private String mainImgUrl;
    private String name;
    private int category;
    private String shortDescription;
    private int isRecruiting;
    private int isLike;

    public static MainPageClubDto toMainPageClubDto(Club club){
        return MainPageClubDto.builder()
                .id(club.getClubId())
                .mainImgUrl(club.getMainImgUrl())
                .name(club.getName())
                .category(club.getCategory())
                .shortDescription(club.getShortDescription())
                .isRecruiting(club.getIsRecruiting())
                .isLike(club.getIsLike())
                .build();
    }

}

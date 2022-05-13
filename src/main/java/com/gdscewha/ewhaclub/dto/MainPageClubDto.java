package com.gdscewha.ewhaclub.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gdscewha.ewhaclub.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Boolean isRecruiting;

}

package com.gdscewha.ewhaclub.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CheckEmailDto {

    @NotBlank
    @NotNull
    private String checkEmail;
}

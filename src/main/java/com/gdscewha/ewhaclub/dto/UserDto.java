package com.gdscewha.ewhaclub.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gdscewha.ewhaclub.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

    private String email;

    private String password;

    private String nickname;

    // Dto -> Entity
    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
        return user;
    }
}

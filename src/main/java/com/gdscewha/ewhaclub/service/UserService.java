package com.gdscewha.ewhaclub.service;

import com.gdscewha.ewhaclub.domain.User;
import com.gdscewha.ewhaclub.dto.CheckEmailDto;
import com.gdscewha.ewhaclub.dto.CheckNameDto;
import com.gdscewha.ewhaclub.dto.MessageResponseDto;
import com.gdscewha.ewhaclub.dto.UserDto;
import com.gdscewha.ewhaclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    /**
     * 회원 가입
     */
    @Transactional
    public MessageResponseDto join(UserDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .nickname(dto.getNickname())
                .build();

        userRepository.save(user);

        return MessageResponseDto.builder().message("sign-up success").build();
    }

    /**
     * 닉네임 중복 검사
     */
    public MessageResponseDto checkDuplicateName(CheckNameDto dto){
        if(userRepository.findByNickName(dto.getCheckName()).size() > 0) {
            return MessageResponseDto.builder().message("already existed nickname").build();
        }
        return MessageResponseDto.builder().message("not existed nickname").build();
    }

    /**
     * 이메일 중복 검사
     */
    public MessageResponseDto checkDuplicateEmail(CheckEmailDto dto){
        if(userRepository.findByEmail(dto.getCheckEmail()).size() > 0) {
            return MessageResponseDto.builder().message("already existed email").build();
        }
        return MessageResponseDto.builder().message("not existed email").build();
    }
}

package com.gdscewha.ewhaclub.service;

import java.util.*;
import java.util.stream.Collectors;

import com.gdscewha.ewhaclub.domain.User;
import com.gdscewha.ewhaclub.dto.*;
import com.gdscewha.ewhaclub.repository.UserRepository;
import com.gdscewha.ewhaclub.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper = new ModelMapper();

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
        if(userRepository.findByNickname(dto.getCheckName()).size() > 0) {
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

    /**
     * User 정보 찾기
     */
    public UserDto findUser(String email){
        List<UserDto> userDto = userRepository.findByEmail(email)
                .stream()
                .map(c -> modelMapper.map(c, UserDto.class))
                .collect(Collectors.toList());
        if(userDto.size() != 1) {
            return null;
        }
        else {
            return userDto.get(0);
        }
    }

    public MessageResponseDto login(LoginInfoDto loginInfoDto){
        UserDto userDto = findUser(loginInfoDto.getEmail());
        if(userDto == null){
            return MessageResponseDto.builder().message("non valid account").build();
        }
        if(!encoder.matches(loginInfoDto.getPassword(), userDto.getPassword())){
            return MessageResponseDto.builder().message("wrong password").build();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginInfoDto.getEmail(), loginInfoDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return MessageResponseDto.builder().message(jwtTokenProvider.createToken(authentication)).build();
    }

}

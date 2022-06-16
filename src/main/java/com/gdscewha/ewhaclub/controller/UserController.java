package com.gdscewha.ewhaclub.controller;

import com.gdscewha.ewhaclub.dto.CheckEmailDto;
import com.gdscewha.ewhaclub.dto.MessageResponseDto;
import com.gdscewha.ewhaclub.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signUp(@Valid @RequestBody UserDto requestDto) {
        return ResponseEntity.ok(userService.join(requestDto))
    }

    @PostMapping("/signup/checkName")
    public ResponseEntity<MessageResponseDto> checkName(@Valid @RequestBody CheckNameDto requestDto) {
        return ResponseEntity.ok(UserService.checkDuplicateName(requestDto))
    }

    @PostMapping("/signup/checkEmail")
    public ResponseEntity<MessageResponseDto> checkEmail(@Valid @RequestBody CheckEmailDto requestDto) {
        return ResponseEntity.ok(UserService.checkDuplicateEmail(requestDto))
    }
}

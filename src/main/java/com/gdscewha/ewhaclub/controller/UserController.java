package com.gdscewha.ewhaclub.controller;

import com.gdscewha.ewhaclub.dto.CheckEmailDto;
import com.gdscewha.ewhaclub.dto.CheckNameDto;
import com.gdscewha.ewhaclub.dto.MessageResponseDto;
import com.gdscewha.ewhaclub.dto.UserDto;
import com.gdscewha.ewhaclub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value="/signup")
    public ResponseEntity<MessageResponseDto> signUp(@Valid @RequestBody UserDto requestDto) {
        return ResponseEntity.ok(userService.join(requestDto));
    }

    @PostMapping(value="/signup/checkName")
    public ResponseEntity<MessageResponseDto> checkName(@Valid @RequestBody CheckNameDto requestDto) {
        return ResponseEntity.ok(userService.checkDuplicateName(requestDto));
    }

    @PostMapping(value="/signup/checkEmail")
    public ResponseEntity<MessageResponseDto> checkEmail(@Valid @RequestBody CheckEmailDto requestDto) {
        return ResponseEntity.ok(userService.checkDuplicateEmail(requestDto));
    }
}

package com.gdscewha.ewhaclub.controller;

import com.gdscewha.ewhaclub.dto.*;
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
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto requestDto) {
        return ResponseEntity.ok(userService.join(requestDto));
    }

    @PostMapping(value="/signup/checkName")
    public ResponseEntity<?> checkName(@Valid @RequestBody CheckNameDto requestDto) {
        return ResponseEntity.ok(userService.checkDuplicateName(requestDto));
    }

    @PostMapping(value="/signup/checkEmail")
    public ResponseEntity<?> checkEmail(@Valid @RequestBody CheckEmailDto requestDto) {
        return ResponseEntity.ok(userService.checkDuplicateEmail(requestDto));
    }

    @PostMapping(value="/signin")
    public ResponseEntity<MessageResponseDto> login(@Valid @RequestBody LoginInfoDto requestDto){
        return ResponseEntity.ok(userService.login(requestDto));
    }
}

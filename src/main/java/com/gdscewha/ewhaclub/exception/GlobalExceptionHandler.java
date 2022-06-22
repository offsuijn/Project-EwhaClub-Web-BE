package com.gdscewha.ewhaclub.exception;

import com.gdscewha.ewhaclub.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gdscewha.ewhaclub.exception.ErrorCode.CLUB_NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> CustomExceptionHandler(CustomException e) {
        log.error("CustomExceptionHandler throw CustomException : {}", e.getErrorCode());
        return ErrorResponseDto.toResponseEntity(e.getErrorCode());
    }
}

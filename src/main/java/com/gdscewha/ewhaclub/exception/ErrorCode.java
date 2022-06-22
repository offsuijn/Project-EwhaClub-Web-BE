package com.gdscewha.ewhaclub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     *  404 NOT_FOUND
     */
    CLUB_NOT_FOUND(NOT_FOUND, "해당 동아리 정보를 찾을 수 없습니다."),
    USER_NOT_FOUND(NOT_FOUND, "해당 회원 정보를 찾을 수 없습니다."),
    LIKE_NOT_FOUND(NOT_FOUND, "좋아요 정보를 찾을 수 없습니다."),
    /**
     *  400 CONFLICT
     */
    LIKE_ALREADY_EXIST(CONFLICT, "좋아요가 이미 눌린 상태입니다."),
    LIKE_ALREADY_NOT_EXIST(CONFLICT, "좋아요가 이미 눌리지 않은 상태입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}

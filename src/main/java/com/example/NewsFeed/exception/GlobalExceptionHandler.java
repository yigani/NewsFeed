package com.example.NewsFeed.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검증 오류 예외 처리
    // 반환 값: { 필드 : 메세지 }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidInputException(MethodArgumentNotValidException exception) {

        Map<String, String> errorResponse = new HashMap<>();

        // 예외 객체에서 필드와 메세지를 꺼내서 Map에 저장
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    errorResponse.put(error.getField(), error.getDefaultMessage());
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // DB에서 데이터를 찾지 못 할 때 예외처리
    // 반환 값: 메세지
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFoundDataException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // 잘못된 입력이 들어올 때 예외처리
    // 반환 값: 메세지
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> invalidInputException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    // 로그인된 유저에게 접근 권한이 없을 때 예외처리
    // 반환 값: 메세지
    @ExceptionHandler(NotLoginUserException.class)
    public ResponseEntity<String> invalidInputException(NotLoginUserException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    // 탈퇴한 유저에 접근 할 경우 예외처리
    // 반환 값: 메세지
    @ExceptionHandler(DeletedUserException.class)
    public ResponseEntity<String> invalidInputException(DeletedUserException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}

package com.example.NewsFeed.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 유효성 검증 오류 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> notBlankException(MethodArgumentNotValidException exception) {
        // json 형태로 반환할 Map 생성
        Map<String, String> errorResponse = new HashMap<>();
        // 예외 객체에서 바인딩한 결과를 필드 단위로 가져온다
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    // 필드와 메세지를 꺼내서 Map에 넣기
                    errorResponse.put(error.getField(), error.getDefaultMessage());
                });
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // 다시 할 것
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Map<String, String>> notBlankException(EntityNotFoundException exception) {
//        // json 형태로 반환할 Map 생성
//        Map<String, String> errorResponse = new HashMap<>();
//        // 예외 객체에서 바인딩한 결과를 필드 단위로 가져온다
//        exception.getBindingResult().getFieldErrors()
//                .forEach(error -> {
//                    // 필드와 메세지를 꺼내서 Map에 넣기
//                    errorResponse.put(error.getField(), error.getDefaultMessage());
//                });
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }
    // TODO: 로그인 검증 관련 예외처리
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<?> handleInvalidCredential(InvalidCredentialException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", e.getMessage()));
    }
}

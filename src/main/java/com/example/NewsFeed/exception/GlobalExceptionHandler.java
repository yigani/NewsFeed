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
        // json 형태로 반환할 Map 생성
        Map<String, String> errorResponse = new HashMap<>();
        // 예외 객체에서 바인딩한 결과를 필드 단위로 가져온다
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    // 필드와 메세지를 꺼내서 Map에 넣기
                    errorResponse.put(error.getField(), error.getDefaultMessage());
                });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // TODO 아래 두 예외는 같은 상황에서 사용 중, 목적을 나눌 필요가 있어 보임

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

    // ResponseStatusException
}

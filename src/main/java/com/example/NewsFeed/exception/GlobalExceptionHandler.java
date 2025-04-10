package com.example.NewsFeed.exception;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> notBlankException(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception
                // 검증 오류 전체 정보를 가져온다.
                .getBindingResult()
                // 모든 오류를 리스트로 반환한다.
                .getAllErrors()
                // 가장 첫 번째 오류만 가져온다.
                .get(0)
                // 어노테이션에 설정한 메세지가 출력된다.
                .getDefaultMessage();
//        Map<String, String> errorMessageList = new HashMap<>() {
//            String errorMessage = exception.getBindingResult().getFieldErrors().forEach(error);
//        };
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}

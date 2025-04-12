package com.example.NewsFeed.controller;

import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.dto.login.LoginRequestDto;
import com.example.NewsFeed.service.LoginServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto dto, HttpServletRequest request) {

        Long userId = loginService.handleLogin(dto);

        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, userId);

        return ResponseEntity.ok("로그인 성공");
    }

    // 로그아웃
    @PostMapping("/users/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 성공");
    }

}

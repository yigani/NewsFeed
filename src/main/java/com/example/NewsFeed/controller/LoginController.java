package com.example.NewsFeed;

import com.example.NewsFeed.dto.users.LoginRequestDto;
import com.example.NewsFeed.service.LoginService;
import com.example.NewsFeed.service.UsersService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/users/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request) {

        Long userId = loginService.handleLogin(dto);

        HttpSession session = request.getSession(); // 신규 세션 생성, JSESSIONID 쿠키 발급
        session.setAttribute("LOGIN_USER", userId); // 서버 메모리에 세션 저장

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/users/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 성공");
    }

}

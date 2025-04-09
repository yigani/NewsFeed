package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserResponseDto;
import com.example.NewsFeed.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;

    // 회원 가입(유저 생성)
    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(@RequestBody SignUpUserRequestDto dto){

        SignUpUserResponseDto signUpUser = usersService.signUp(dto);

        return new ResponseEntity<>(signUpUser, HttpStatus.CREATED);
    }

    // 프로필 생성

    // 비밀번호 변경


    // 회원 탈퇴


}

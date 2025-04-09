package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.CreateProfileRequestDto;
import com.example.NewsFeed.dto.users.CreateProfileResponseDto;
import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserResponseDto;
import com.example.NewsFeed.service.S;
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

    //Todo 유저가 생성되어야 프로필도 생성할 수 있어야하면 필터처리를 해야하는 걸까요?

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@RequestBody CreateProfileRequestDto dto){

        CreateProfileResponseDto createdProfile = usersService.createProfile(dto);

        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);

    }

    // 비밀번호 변경


    // 회원 탈퇴


}

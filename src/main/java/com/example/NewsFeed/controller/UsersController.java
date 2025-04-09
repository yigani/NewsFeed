package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.service.S;
import com.example.NewsFeed.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //가입할때 프로필을 입력하는 경우/입력하지 않는 경우에 따라 유저식별자를 다르게 받아와야 할 것?? 같습니다.

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@RequestBody CreateProfileRequestDto dto){

        CreateProfileResponseDto createdProfile = usersService.createProfile(dto);

        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);

    }

    // 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestParam Long id, @RequestBody UpdatePasswordRequestDto dto) {

        usersService.updatePassword(id,dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 회원 탈퇴


}

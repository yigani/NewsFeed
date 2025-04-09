package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.*;
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
    @PatchMapping("/me/status")
    public ResponseEntity<DeactivateUserResponseDto> deactivateUser(@RequestParam Long id, @RequestBody DeactivateUserRequestDto dto) {

        DeactivateUserResponseDto deactivateUserResponseDto = usersService.deactivateUser(id, dto);

        return new ResponseEntity<>(deactivateUserResponseDto, HttpStatus.OK);
    }

}

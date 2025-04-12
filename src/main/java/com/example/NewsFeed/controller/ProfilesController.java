package com.example.NewsFeed.controller;

import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.service.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfileServiceImpl profileService;

    // id로 유저 프로필 조회
    @GetMapping("/{id}/profile")
    public ResponseEntity<ProfileResponseDto> userProfile(@PathVariable Long id) {
        ProfileResponseDto profileResponseDto = profileService.userProfile(id);
        return new ResponseEntity<>(profileResponseDto, HttpStatus.OK);
    }


    // 로그인된 유저 프로필 수정
    @PatchMapping("/me")
    public ResponseEntity<MyProfileUpdateResponseDto> updateProfile(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @RequestBody MyProfileUpdateRequestDto myprofileUpdate
    ){

        MyProfileUpdateResponseDto responseDto = profileService.updateProfile(userId, myprofileUpdate);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}

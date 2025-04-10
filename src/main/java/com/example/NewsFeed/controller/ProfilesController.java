package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.service.ProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfileServiceImpl profileService;

    //프로필 조회
    @GetMapping("/{id}/profile")
    public ResponseEntity<ProfileResponseDto> userProfile(@PathVariable Long id){
        ProfileResponseDto profileResponseDto = profileService.userProfile(id);
        return new ResponseEntity<>(profileResponseDto, HttpStatus.OK);
    }

    //내 프로필 수정
    //테스트를 위해 PathVariable로 아이디 값을 넣어서 확인
    //로그인 구현시 userId부분 "/me"로 수정필요
    @PatchMapping("/me/{userId}")
    public ResponseEntity<MyProfileUpdateResponseDto> updateProfile(
            @PathVariable Long userId,
            @RequestBody MyProfileUpdateRequestDto myprofileUpdate
    ){
        MyProfileUpdateResponseDto responseDto = profileService.updateProfile(userId, myprofileUpdate);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}

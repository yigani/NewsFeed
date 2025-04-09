package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.CreateProfileRequestDto;
import com.example.NewsFeed.dto.users.CreateProfileResponseDto;
import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserResponseDto;

public interface UsersService {

    // 회원 가입(유저생성)
    SignUpUserResponseDto signUp(SignUpUserRequestDto dto);

    CreateProfileResponseDto createProfile(CreateProfileRequestDto dto);

    // 프로필 생성

    // 비밀번호 변경

    // 회원 탈퇴

}

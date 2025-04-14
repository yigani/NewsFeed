package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;

public interface ProfileService {

    ProfileResponseDto userProfile(Long userId);

    MyProfileUpdateResponseDto updateProfile(Long userId, MyProfileUpdateRequestDto requestDto);
}

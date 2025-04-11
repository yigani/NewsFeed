package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.repository.ProfilesRepository;
import org.apache.catalina.User;

public interface ProfileService {

    ProfileResponseDto userProfile(Long userId);

    MyProfileUpdateResponseDto updateProfile(Long userId, MyProfileUpdateRequestDto requestDto);
}

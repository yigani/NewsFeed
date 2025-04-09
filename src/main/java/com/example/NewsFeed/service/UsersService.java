package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;

public interface UsersService {

    UserResponseDto myUserInfo(Long userId);

    UserInfoResponseDto userInfo(Long userId);

}

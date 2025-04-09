package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.UserResponseDto;

public interface UsersService {

    UserResponseDto myUserInfo(Long userId);

}

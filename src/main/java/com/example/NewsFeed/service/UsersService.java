package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;
import com.example.NewsFeed.dto.users.UserSearchResponseDto;
import com.example.NewsFeed.entity.Users;

import java.util.List;

public interface UsersService {

    UserResponseDto myUserInfo(Long userId);

    UserInfoResponseDto userInfo(Long userId);

    List<UserSearchResponseDto> searchUser(String name);
}

package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.*;

public interface UsersService {

    SignUpUserResponseDto signUp(SignUpUserRequestDto dto);

    CreateProfileResponseDto createProfile(Long id, CreateProfileRequestDto dto);

    void updatePassword(Long id, UpdatePasswordRequestDto dto);

    DeactivateUserResponseDto deactivateUser(Long id, DeactivateUserRequestDto dto);

}

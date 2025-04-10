package com.example.NewsFeed.dto.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class UserResponseDto {

    private final String userName;
    private final String email;

}

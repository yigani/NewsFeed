package com.example.NewsFeed.dto.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSearchResponseDto {

    private final Long id;
    private final String userName;
}

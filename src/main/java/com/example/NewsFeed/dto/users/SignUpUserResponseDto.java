package com.example.NewsFeed.dto.users;

import com.example.NewsFeed.entity.Users;
import lombok.Getter;

@Getter
public class SignUpUserResponseDto {

    private final String email;

    private final String username;

    public SignUpUserResponseDto(Users user) {
        this.email = user.getEmail();
        this.username = user.getUserName();
    }
}

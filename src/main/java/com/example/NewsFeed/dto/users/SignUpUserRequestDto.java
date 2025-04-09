package com.example.NewsFeed.dto.users;

import lombok.Getter;

@Getter
public class SignUpUserRequestDto {

    private final String email;

    private final String password;

    private final String username;


    public SignUpUserRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}

package com.example.NewsFeed.dto.users;

import lombok.Getter;

@Getter
public class DeactivateUserRequestDto {

    private final String password;

    public DeactivateUserRequestDto(String password) {
        this.password = password;
    }
}

package com.example.NewsFeed.dto.users;

import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    private final String password;
    private final String newPassword;

    public UpdatePasswordRequestDto(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }
}

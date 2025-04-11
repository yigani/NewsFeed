package com.example.NewsFeed.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginResponseDto {


    @NotNull
    private final String email;

    @NotNull
    private final String password;

    public LoginResponseDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

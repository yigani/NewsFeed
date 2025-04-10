package com.example.NewsFeed.dto.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeactivateUserRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;

    public DeactivateUserRequestDto(String password) {
        this.password = password;
    }
}

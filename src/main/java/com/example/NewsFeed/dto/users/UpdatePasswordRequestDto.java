package com.example.NewsFeed.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(max = 100, message = "비밀번호는 30글자 이하로 입력해주세요.")
    private final String password;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Size(max = 100, message = "비밀번호는 30글자 이하로 입력해주세요.")
    private final String newPassword;

    public UpdatePasswordRequestDto(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }
}

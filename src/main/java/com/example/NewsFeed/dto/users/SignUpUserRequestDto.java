package com.example.NewsFeed.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpUserRequestDto {

    @NotBlank(message = "이메일을 입력해주세요")
    @Size(max = 50, message = "이메일은 16글자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(max = 100, message = "비밀번호는 30글자 이하로 입력해주세요.")
    private final String password;

    @NotBlank(message = "이름을 입력해주세요")
    @Size(max = 30, message = "이름은 10글자 이하로 입력해주세요.")
    private final String username;

    public SignUpUserRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}

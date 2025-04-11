package com.example.NewsFeed.dto.users;

import com.example.NewsFeed.config.Gender;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateProfileRequestDto {

    private int gender;
    private LocalDate birthday;
    @Size(max = 100, message = "소개는 30글자 이하로 입력해주세요.")
    private String introduction;
    @Size(max = 255, message = "올바른 이미지 주소를 입력해주세요.")
    private String image;

}

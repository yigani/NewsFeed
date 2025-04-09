package com.example.NewsFeed.dto.users;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateProfileRequestDto {

    private final String gender;
    private final LocalDate birthday;
    private final String introduction;
    private final String image;

    public CreateProfileRequestDto(String gender, LocalDate birthday, String introduction, String image) {
        this.gender = gender;
        this.birthday = birthday;
        this.introduction = introduction;
        this.image = image;
    }
}

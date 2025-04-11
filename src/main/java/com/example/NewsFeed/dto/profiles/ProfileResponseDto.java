package com.example.NewsFeed.dto.profiles;

import com.example.NewsFeed.config.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class ProfileResponseDto {

    private final Gender gender;
    private final LocalDate birthday;
    private final String introduction;
    private final String image;

}

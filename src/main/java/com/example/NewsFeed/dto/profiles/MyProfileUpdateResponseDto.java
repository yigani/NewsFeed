package com.example.NewsFeed.dto.profiles;

import com.example.NewsFeed.config.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MyProfileUpdateResponseDto {

    private final UserName users;
    private final UserProfile profiles;

    @Getter
    @RequiredArgsConstructor
    public static class UserName {
        private final String username;
    }

    @Getter
    @RequiredArgsConstructor
    public static class UserProfile {

        private final Gender gender;
        private final LocalDate birthday;
        private final String introduction;
        private final String image;
    }
}

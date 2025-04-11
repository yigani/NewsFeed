package com.example.NewsFeed.dto.profiles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MyProfileUpdateResponseDto {

    private final UserName user;
    private final UserProfile profile;

    @Getter
    @RequiredArgsConstructor
    public static class UserName {

        private final String userName;

    }

    @Getter
    @RequiredArgsConstructor
    public static class UserProfile {

        private final String gender;
        private final LocalDate birthDay;
        private final String introduction;
        private final String image;

    }

}

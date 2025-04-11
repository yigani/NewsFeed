package com.example.NewsFeed.dto.profiles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MyProfileUpdateRequestDto {

    //음... 이미 username과 userprofile을 받아오는 dto가 있는데 따로 생성해야할까?
    //내일 튜터님께 질문해보기
    private final UserName users;
    private final UserProfile profiles;

    @Getter
    @RequiredArgsConstructor
    public static class UserName{

        private final String username;

    }

    @Getter
    @RequiredArgsConstructor
    public static class UserProfile{
        private final int gender;
        private final LocalDate birthday;
        private final String introduction;
        private final String image;
    }

}

package com.example.NewsFeed.dto.profiles;

import com.example.NewsFeed.dto.users.UserResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MyProfileUpdateRequestDto {

    //음... 이미 username과 userprofile을 받아오는 dto가 있는데 따로 생성해야할까?
    //내일 튜터님께 질문해보기
    private final UserName user;
    private final UserProfile profile;

    @Getter
    @RequiredArgsConstructor
    public static class UserName{

        private final String userName;

    }

    @Getter
    @RequiredArgsConstructor
    public static class UserProfile{
        private final String gender;
        private final LocalDate birthDay;
        private final String introduction;
        private final String image;
    }

}

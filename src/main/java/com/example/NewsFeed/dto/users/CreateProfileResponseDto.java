package com.example.NewsFeed.dto.users;

import com.example.NewsFeed.entity.Profiles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProfileResponseDto {

    private String gender;
    private LocalDate birthday;
    private String introduction;
    private String image;

    public CreateProfileResponseDto(Profiles profile) {
        this.gender = profile.getGender();
        this.birthday = profile.getBirthday();
        this.introduction = profile.getIntroduction();
        this.image = profile.getImage();
    }
}

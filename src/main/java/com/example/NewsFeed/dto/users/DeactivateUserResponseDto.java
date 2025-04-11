package com.example.NewsFeed.dto.users;

import com.example.NewsFeed.entity.Users;
import lombok.Getter;

@Getter
public class DeactivateUserResponseDto {

    private final boolean isDeleted;

    public DeactivateUserResponseDto(Users users) {
        this.isDeleted = users.isDelete();
    }
}

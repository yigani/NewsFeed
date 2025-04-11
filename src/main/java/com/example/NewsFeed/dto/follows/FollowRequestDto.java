package com.example.NewsFeed.dto.follows;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowRequestDto {

    private Long fromUserId;
    private Long toUserId;
}

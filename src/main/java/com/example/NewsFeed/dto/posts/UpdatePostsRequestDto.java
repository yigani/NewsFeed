package com.example.NewsFeed.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostsRequestDto {

    private String title;
    private String contents;
}

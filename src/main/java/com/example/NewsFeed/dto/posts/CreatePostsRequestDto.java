package com.example.NewsFeed.dto.posts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostsRequestDto {

    private String title;
    private String contents;

}

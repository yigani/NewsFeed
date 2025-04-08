package com.example.NewsFeed.dto.Posts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchPostsRequestDto {

    private String title;
    private String contents;
}

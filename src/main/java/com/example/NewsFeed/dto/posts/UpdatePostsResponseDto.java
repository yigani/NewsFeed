package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

@Getter
public class UpdatePostsResponseDto {
    private Long id;
    private String title;
    private String contents;

    public UpdatePostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
    }
}
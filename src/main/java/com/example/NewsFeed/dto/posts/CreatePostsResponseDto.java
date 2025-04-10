package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostsResponseDto {
    private String title;
    private String contents;
    private LocalDateTime createAt;

    public CreatePostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
    }
}
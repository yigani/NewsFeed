package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createAt;

    public CreatePostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
    }
}
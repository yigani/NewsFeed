package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostsResponseDto {
    private Long id;
    private String title;
    private String contents;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updateAt;

    public UpdatePostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }
}
package com.example.NewsFeed.dto.Posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PatchPostsResponseDto {

    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public PatchPostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }
}

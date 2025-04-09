package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindByIdPostsResponseDto {

    private String username;
    private String title;
    private String contents;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public FindByIdPostsResponseDto(Posts posts, String username) {
        this.username = username;
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }
}

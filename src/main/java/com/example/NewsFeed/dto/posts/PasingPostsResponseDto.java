package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PasingPostsResponseDto {

    private Long id;

    private String username;
    private String title;
    private String contents;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updateAt;

    public PasingPostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.username = posts.getUser().getUserName(); // user에서 꺼내옴
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }
}

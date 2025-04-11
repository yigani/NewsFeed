package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindByIdPostsResponseDto {

    // TODO 이거 카멜 케이스 해야하는지 여부
    private String username;
    private String title;
    private String contents;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime updateAt;

    public FindByIdPostsResponseDto(Posts posts, String username) {
        this.username = username;
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }

}
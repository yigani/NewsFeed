package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindByIdPostsResponseDto {

    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // 이거 하나만 있으면 되나??
    public FindByIdPostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.username = posts.getUser().getUserName(); // user에서 꺼내옴
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createAt = posts.getCreateAt();
        this.updateAt = posts.getUpdateAt();
    }
}
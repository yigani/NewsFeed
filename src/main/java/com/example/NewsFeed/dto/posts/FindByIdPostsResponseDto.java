package com.example.NewsFeed.dto.posts;

import com.example.NewsFeed.entity.Posts;
import lombok.Getter;

@Getter
public class FindByIdPostsResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;

    public FindByIdPostsResponseDto(Posts posts, String userName) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.writer = userName;
    }
}
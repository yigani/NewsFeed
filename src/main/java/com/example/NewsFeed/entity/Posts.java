package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import com.example.NewsFeed.dto.posts.UpdatePostsRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "posts")
public class Posts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @NotNull
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    // 생성자: CreatePostsRequestDto 기반
    public Posts(CreatePostsRequestDto dto, Users user) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.user = user;
    }

    public void update(UpdatePostsRequestDto updatePostsRequestDto) {
        this.title = updatePostsRequestDto.getTitle();
        this.contents = updatePostsRequestDto.getContents();
    }
}
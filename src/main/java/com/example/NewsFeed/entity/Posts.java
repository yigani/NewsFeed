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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    public Posts(CreatePostsRequestDto createPostsRequestDto, Users users) {
        this.userId = users;
        this.title = createPostsRequestDto.getTitle();
        this.contents = createPostsRequestDto.getContents();
    }

    public void update(UpdatePostsRequestDto updatePostsRequestDto) {
        this.title = updatePostsRequestDto.getTitle();
        this.contents = updatePostsRequestDto.getContents();
    }
}
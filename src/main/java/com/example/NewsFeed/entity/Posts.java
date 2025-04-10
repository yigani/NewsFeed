package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String title;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String contents;

    public Posts(CreatePostsRequestDto createPostsRequestDto, Users users) {
        this.userId = users;
        this.title = createPostsRequestDto.getTitle();
        this.contents = createPostsRequestDto.getContents();
    }
}
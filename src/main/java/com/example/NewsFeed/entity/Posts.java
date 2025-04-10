package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
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

    // 수정 편의 메서드
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
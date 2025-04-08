package com.example.NewsFeed.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "follows")
public class Follows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private Users following;
}

// 안녕하세요.
// 좋은 하루입니다.
// 곧 점심 시간인데 식사 맛있게 드세요.
// 감사합니다.

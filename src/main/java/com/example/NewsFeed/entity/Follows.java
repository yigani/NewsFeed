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
    @JoinColumn(nullable = false, name = "follower")
    private Users follower;

    @ManyToOne
    @JoinColumn(nullable = false, name = "following")
    private Users following;

    public Follows(Users follower, Users following){
        this.follower = follower;
        this.following = following;
    }
}
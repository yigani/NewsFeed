package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findAllByOrderByCreateAtDesc(Pageable pageable);
}
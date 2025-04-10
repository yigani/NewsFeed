package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends CrudRepository<Posts, Long> {

    // 사용자 ID로 게시글 전체 조회
    List<Posts> findAllByUserId(Long userId);

    // 전체 게시글 조회
    List<Posts> findAll();
}
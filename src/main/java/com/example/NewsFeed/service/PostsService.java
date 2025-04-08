package com.example.NewsFeed.service;

import com.example.NewsFeed.entity.Posts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    ResponseEntity<Posts> create();
    ResponseEntity<Posts> findById();
    ResponseEntity<Posts> updateById();
    void deleteById();
}

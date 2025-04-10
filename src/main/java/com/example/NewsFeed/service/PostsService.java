package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Users;


import java.util.List;

public interface PostsService {
    CreatePostsResponseDto create(CreatePostsRequestDto dto, Users user);
    FindByIdPostsResponseDto findById(Long postId);
    UpdatePostsResponseDto updateById(UpdatePostsRequestDto dto, Long postId);
    void deleteById(Long postId);
    List<FindByIdPostsResponseDto> findAllByUser(Long userId);
    List<FindByIdPostsResponseDto> findAll();
}
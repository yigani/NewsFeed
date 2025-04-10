package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Users;
import org.springframework.stereotype.Service;

public interface PostsService {
    CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users);
    FindByIdPostsResponseDto findById(Long id);
    UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id);
    void deleteById(Long id);
}

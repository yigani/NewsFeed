package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsService {
    CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users);
    FindByIdPostsResponseDto findById(Long id);
    UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id, Users users);
    void deleteById(Long id, Users users);
    Page<PasingPostsResponseDto> getNewsFeed(Pageable pageable);
}
package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostsService {
    CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Long userId);

    FindByIdPostsResponseDto findById(Long id);

    UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id, Long userId);

    void deleteById(Long id, Long userId);

    Page<PasingPostsResponseDto> getNewsFeed(Pageable pageable);
}
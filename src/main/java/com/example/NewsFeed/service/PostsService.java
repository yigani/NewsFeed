package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import com.example.NewsFeed.dto.posts.CreatePostsResponseDto;
import com.example.NewsFeed.dto.posts.FindByIdPostsResponseDto;
import com.example.NewsFeed.dto.posts.UpdatePostsResponseDto;
import com.example.NewsFeed.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface PostsService {
    CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users);
    FindByIdPostsResponseDto findById(Long id);
    UpdatePostsResponseDto updateById(Long id);
    void deleteById(Long id);
}

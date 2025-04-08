package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import com.example.NewsFeed.dto.posts.CreatePostsResponseDto;
import com.example.NewsFeed.dto.posts.FindByIdPostsResponseDto;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    // TODO 로그인 이후에 @SessionAttribute () 내부의 Users 수정
    @PostMapping
    public ResponseEntity<CreatePostsResponseDto> createPosts(@RequestBody CreatePostsRequestDto createPostsRequestDto, @SessionAttribute(name = "Users") Users users) {
        // Service
        CreatePostsResponseDto createPostsResponseDto = postsService.create(createPostsRequestDto, users);
        return new ResponseEntity<>(createPostsResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindByIdPostsResponseDto> findPostsById(@PathVariable Long id) {
        FindByIdPostsResponseDto findByIdPostsResponseDto = postsService.findById(id);
        return new ResponseEntity<>(findByIdPostsResponseDto, HttpStatus.OK);
    }


}

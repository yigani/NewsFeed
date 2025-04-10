package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.posts.*;
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
    public ResponseEntity<CreatePostsResponseDto> createPost(@RequestBody CreatePostsRequestDto createPostsRequestDto, @SessionAttribute(name = "Users") Users users) {
        // Service
        CreatePostsResponseDto createPostsResponseDto = postsService.create(createPostsRequestDto, users);
        return new ResponseEntity<>(createPostsResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<FindByIdPostsResponseDto> findPostById(@PathVariable Long postId) {
        FindByIdPostsResponseDto findByIdPostsResponseDto = postsService.findById(postId);
        return new ResponseEntity<>(findByIdPostsResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/postId")
    public ResponseEntity<UpdatePostsResponseDto> updatePostById(@RequestBody UpdatePostsRequestDto updatePostsRequestDto, @PathVariable Long postId) {
        UpdatePostsResponseDto updatePostsResponseDto = postsService.updateById(updatePostsRequestDto, postId);
        return new ResponseEntity<>(updatePostsResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/postId")
    public void deletePostById(@PathVariable Long postId) {
        postsService.deleteById(postId);
    }
}

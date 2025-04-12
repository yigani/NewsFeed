package com.example.NewsFeed.controller;

import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.service.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<CreatePostsResponseDto> createPost(@Valid @RequestBody CreatePostsRequestDto createPostsRequestDto,
                                                             @SessionAttribute(name = Const.LOGIN_USER) Long userId) {
        CreatePostsResponseDto createPostsResponseDto = postsService.create(createPostsRequestDto, userId);
        return new ResponseEntity<>(createPostsResponseDto, HttpStatus.OK);
    }

    // 게시글 id로 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<FindByIdPostsResponseDto> findPostById(@PathVariable Long postId) {
        FindByIdPostsResponseDto findByIdPostsResponseDto = postsService.findById(postId);
        return new ResponseEntity<>(findByIdPostsResponseDto, HttpStatus.OK);
    }

    // 게시글 id로 게시글을 찾아서 게시글을 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<UpdatePostsResponseDto> updatePostById(@Valid @RequestBody UpdatePostsRequestDto updatePostsRequestDto,
                                                                 @PathVariable Long postId,
                                                                 @SessionAttribute(name = Const.LOGIN_USER) Long userId) {
        UpdatePostsResponseDto updatePostsResponseDto = postsService.updateById(updatePostsRequestDto, postId, userId);
        return new ResponseEntity<>(updatePostsResponseDto, HttpStatus.OK);
    }

    // 게시글 id로 게시글을 찾아서 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@SessionAttribute(name = Const.LOGIN_USER) Long userId,
                                               @PathVariable Long postId) {
        postsService.deleteById(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<Page<PasingPostsResponseDto>> getNewsFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(postsService.getNewsFeed(pageable));
    }

    @GetMapping("/newsfeed")
    public Page<PasingPostsResponseDto> getNewsFeed(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return postsService.getNewsFeed(pageable);
    }
}

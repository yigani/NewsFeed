package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.UsersRepository;
import com.example.NewsFeed.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.NewsFeed.entity.Posts;
import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final UsersRepository usersRepository; //  테스트용 사용자 불러오기

    //  게시글 생성 (세션 없이 테스트용 유저 직접 할당)
    @PostMapping
    public ResponseEntity<CreatePostsResponseDto> createPost(@RequestBody CreatePostsRequestDto requestDto) {
        // 기존 코드 스타일 유지하면서, email로 조회
        Users testUser = usersRepository.findByEmail("test@example.com")
                .orElseThrow(() -> new RuntimeException("테스트용 유저를 찾을 수 없습니다."));

        CreatePostsResponseDto responseDto = postsService.create(requestDto, testUser);
        return ResponseEntity.ok(responseDto);
    }

    //  게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<FindByIdPostsResponseDto> findPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postsService.findById(postId));
    }

    //  게시글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<UpdatePostsResponseDto> updatePostById(@RequestBody UpdatePostsRequestDto updatePostsRequestDto, @PathVariable Long postId) {
        UpdatePostsResponseDto updatePostsResponseDto = postsService.updateById(updatePostsRequestDto, postId);
        return new ResponseEntity<>(updatePostsResponseDto, HttpStatus.OK);
    }

    //  게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        postsService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

    //  ID별 게시글 목록 조회
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<FindByIdPostsResponseDto>> getPostsByUserId(@PathVariable Long userId) {
        List<FindByIdPostsResponseDto> posts = postsService.findAllByUser(userId);
        return ResponseEntity.ok(posts);
    }

    /* 내 게시글 목록 조회 (테스트라 잠시 죽여놓음)
     @GetMapping("/me")
     public ResponseEntity<List<FindByIdPostsResponseDto>> getMyPosts(@SessionAttribute(name = "Users") Users user) {
         List<FindByIdPostsResponseDto> posts = postsService.findAllByUser(user.getId());
         return ResponseEntity.ok(posts);
     }
     */

    //  전체 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<FindByIdPostsResponseDto>> getAllPosts() {
        List<FindByIdPostsResponseDto> posts = postsService.findAll();
        return ResponseEntity.ok(posts);
    }
}
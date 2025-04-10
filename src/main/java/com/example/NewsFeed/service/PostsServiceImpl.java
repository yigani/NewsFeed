package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Posts;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.PostsRepository;
import com.example.NewsFeed.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @Override
    public CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users) {
        // requestDto와 필드값 user 입력
        Posts posts = new Posts(createPostsRequestDto, users);
        CreatePostsResponseDto createPostsResponseDto = new CreatePostsResponseDto(posts);
        return createPostsResponseDto;
    }

    @Override
    public FindByIdPostsResponseDto findById(Long id) {
        // 게시글의 id를 기준으로 post 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        // 게시글의 userId를 기준으로 user 조회
        Users users = usersRepository.findById(posts.getId())
                .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
        // 반환할 dto로 변환
        FindByIdPostsResponseDto findByIdPostsResponseDto = new FindByIdPostsResponseDto(posts, users.getUserName());
        return findByIdPostsResponseDto;
    }

    @Override
    public UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id) {
        // id에 맞는 posts 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        // 조회한 posts에 requestDto 저장
        Posts updatePosts = postsRepository.save(posts);
        // 반환할 dto로 변환
        UpdatePostsResponseDto updatePostsResponseDto = new UpdatePostsResponseDto(updatePosts);
        return updatePostsResponseDto;
    }

    @Override
    public void deleteById(Long id) {
        // postsRepository에 id가 없다면 예외처리
        if(!postsRepository.existsById(id)) {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        // postsRepository에서 id가 있는 행을 삭제
        postsRepository.deleteById(id);
    }
}

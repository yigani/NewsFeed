package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Posts;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.PostsRepository;
import com.example.NewsFeed.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    // userId를 참조하여 게시글 생성
    @Override
    public CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users) {
        Posts posts = new Posts(createPostsRequestDto, users);
        Posts savedPosts = postsRepository.save(posts); // ✅ 저장
        return new CreatePostsResponseDto(savedPosts);  // 저장된 객체로 응답
    }

    // id로 게시글을 단건 조회
    @Override
    public FindByIdPostsResponseDto findById(Long id) {
        // 게시글의 id를 기준으로 post 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        // 게시글의 userId를 기준으로 user 조회
        Users users = usersRepository.findById(posts.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
        return new FindByIdPostsResponseDto(posts);
    }

    // id로 게시글을 찾아서 수정
    @Override
    public UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id) {
        // id에 맞는 posts 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        // 조회한 posts에 requestDto 저장
        posts.update(updatePostsRequestDto);
        Posts updatePosts = postsRepository.save(posts);
        return new UpdatePostsResponseDto(updatePosts);
    }

    // id로 게시글을 찾아서 삭제
    @Override
    public void deleteById(Long id) {
        // postsRepository에 id가 없다면 예외처리
        if(!postsRepository.existsById(id)) {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        // postsRepository에서 id가 있는 행을 삭제
        postsRepository.deleteById(id);
    }

    // 페이징 관련
    @Override
    public Page<FindByIdPostsResponseDto> getNewsFeed(Pageable pageable) {
        return postsRepository.findAllByOrderByCreateAtDesc(pageable)
                .map(FindByIdPostsResponseDto::new);
    }
}



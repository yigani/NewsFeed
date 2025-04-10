package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Posts;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.PostsRepository;
import com.example.NewsFeed.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    @Override
    public CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Users users) {
        Posts posts = new Posts(createPostsRequestDto, users);
        Posts savedPosts = postsRepository.save(posts); // ✅ 저장
        return new CreatePostsResponseDto(savedPosts);  // 저장된 객체로 응답
    }

    @Override
    public FindByIdPostsResponseDto findById(Long id) {
        // 게시글의 id를 기준으로 post 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        // 게시글의 userId를 기준으로 user 조회
        Users users = usersRepository.findById(posts.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
        // 반환할 dto로 변환
        FindByIdPostsResponseDto findByIdPostsResponseDto = new FindByIdPostsResponseDto(posts, users.getUserName());
        return findByIdPostsResponseDto;
    }

    @Override
    public UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        posts.setTitle(updatePostsRequestDto.getTitle());
        posts.setContents(updatePostsRequestDto.getContents());

        Posts updatedPosts = postsRepository.save(posts);

        return new UpdatePostsResponseDto(updatedPosts);
    }

    @Override
    public void deleteById(Long id) {
        // postsRepository에 id가 없다면 예외처리
        if (!postsRepository.existsById(id)) {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
        // postsRepository에서 id가 있는 행을 삭제
        postsRepository.deleteById(id);
    }

    @Override
    public List<FindByIdPostsResponseDto> findAllByUser(Long userId) {
        // userId로 작성된 게시글 리스트 조회
        List<Posts> postsList = postsRepository.findAllByUserId(userId);

        // 게시글에 연결된 유저 이름을 각 post마다 조회해 dto로 변환
        return postsList.stream()
                .map(post -> {
                    Users users = usersRepository.findById(post.getUser().getId())
                            .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
                    return new FindByIdPostsResponseDto(post, users.getUserName());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FindByIdPostsResponseDto> findAll() {
        // 전체 게시글 조회
        List<Posts> postsList = postsRepository.findAll();

        // 각 게시글에 연결된 유저 이름을 조회하여 dto로 변환
        return postsList.stream()
                .map(post -> {
                    Users users = usersRepository.findById(post.getUser().getId())
                            .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
                    return new FindByIdPostsResponseDto(post, users.getUserName());
                })
                .collect(Collectors.toList());
    }
}
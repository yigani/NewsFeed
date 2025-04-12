package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.*;
import com.example.NewsFeed.entity.Posts;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.exception.NotLoginUserException;
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
    public CreatePostsResponseDto create(CreatePostsRequestDto createPostsRequestDto, Long userId) {

        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("로그인한 유저를 찾을 수 없습니다."));

        Posts posts = new Posts(createPostsRequestDto, users);

        return new CreatePostsResponseDto(postsRepository.save(posts));
    }

    // id로 게시글을 단건 조회
    @Override
    public FindByIdPostsResponseDto findById(Long id) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
        Users users = usersRepository.findById(posts.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("게시글 작성자를 찾을 수 없습니다."));
        return new FindByIdPostsResponseDto(posts, users.getUserName());
    }

    // id로 조회한 게시글이 로그인된 유저의 게시글이 맞다면 수정
    @Override
    public UpdatePostsResponseDto updateById(UpdatePostsRequestDto updatePostsRequestDto, Long id, Long userId) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (!posts.getUser().getId().equals(userId)) {
            throw new NotLoginUserException("타인의 게시글은 수정할 수 없습니다.");
        }
        posts.update(updatePostsRequestDto);

        return new UpdatePostsResponseDto(postsRepository.save(posts));
    }

    // id로 조회한 게시글이 로그인된 유저의 게시글이 맞다면 삭제
    @Override
    public void deleteById(Long id, Long userId) {

        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        if (!posts.getUser().getId().equals(userId)) {
            throw new NotLoginUserException("타인의 게시글은 삭제할 수 없습니다.");
        }

        postsRepository.deleteById(id);
    }

    // 페이징 관련
    @Override
    public Page<PasingPostsResponseDto> getNewsFeed(Pageable pageable) {
        return postsRepository.findAllByOrderByCreateAtDesc(pageable)
                .map(PasingPostsResponseDto::new);
    }
}

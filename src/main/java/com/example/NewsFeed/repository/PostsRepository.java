package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Posts;
import org.springframework.data.domain.Page; //페이징을 위한 클래스
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; //페이징을 처리하는 인터페이스
// org.springframework.data.domain.PageRequest;
// 현재 페이지와 한 페이지에 보여 줄 게시물 개수 등을 설정하여 페이징 요청을 하는 클래스이다.

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findAllByOrderByCreateAtDesc(Pageable pageable);
    // PageRequest.of(page, size)를 쓰면 자동으로 Pageable 객체가 생성된다고 한다.
}
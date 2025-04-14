package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Follows;
import com.example.NewsFeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    // user가 팔로잉한 목록을 조회할 때 isDelete가 False인 유저만 조회
    List<Follows> findByFollowerAndFollowingIsDeleteFalse(Users follower);

    // user의 팔로워 목록을 조회할 때 isDelete가 False인 유저만 조회
    List<Follows> findByFollowingAndFollowerIsDeleteFalse(Users following);

    // 팔로우 생성
    boolean existsByFollowerAndFollowing(Users follower, Users following);

    // 팔로우 삭제
    Optional<Follows> findByFollowerAndFollowing(Users follower, Users following);

    // user가 팔로잉한 유저의 수를 조회할 때 isDelete가 False인 유저만 조회
    int countByFollowerAndFollowingIsDeleteFalse(Users user);

    // user를 팔로잉한 유저의 수를 조회할 때 isDelete가 False인 유저만 조회
    int countByFollowingAndFollowerIsDeleteFalse(Users user);

}

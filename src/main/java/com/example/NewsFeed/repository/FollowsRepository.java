package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Follows;
import com.example.NewsFeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    List<Follows> findByFollower(Users follower);

    List<Follows> findByFollowing(Users following);

}

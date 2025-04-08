package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<Follows, Long> {
}

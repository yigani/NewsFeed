package com.example.NewsFeed.service;

import com.example.NewsFeed.entity.Follows;
import com.example.NewsFeed.entity.Users;

import java.util.List;
import java.util.Optional;

public interface FollowsService {

    void follow(Long fromUserId, Long toUserId);

    void unfollow(Long fromUserId, Long toUserId);

    List<String> followingUserNames(Long userId);

    List<String> followerUserNames(Long userId);

    int followerCount(Long userId);

    int followingCount(Long userId);

}

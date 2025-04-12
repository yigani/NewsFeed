package com.example.NewsFeed.service;

import java.util.List;

public interface FollowsService {

    void follow(Long fromUserId, Long toUserId);

    void unfollow(Long fromUserId, Long toUserId);

    List<String> followingUserNames(Long userId);

    List<String> followerUserNames(Long userId);

    int followerCount(Long userId);

    int followingCount(Long userId);
}

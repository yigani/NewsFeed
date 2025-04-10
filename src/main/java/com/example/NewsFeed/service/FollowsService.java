package com.example.NewsFeed.service;

import java.util.List;

public interface FollowsService {

    List<String> followingUserNames(Long userId);

    List<String> followerUserNames(Long userId);

}

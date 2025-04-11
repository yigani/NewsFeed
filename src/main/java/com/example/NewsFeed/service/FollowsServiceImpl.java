package com.example.NewsFeed.service;

import com.example.NewsFeed.entity.Follows;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.FollowsRepository;
import com.example.NewsFeed.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowsServiceImpl implements FollowsService{


    private final UsersRepository usersRepository;
    private final FollowsRepository followsRepository;

    public FollowsServiceImpl(UsersRepository usersRepository , FollowsRepository followsRepository) {
        this.usersRepository = usersRepository;
        this.followsRepository = followsRepository;
    }

    @Override
    public List<String> followingUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉한 유저가 없습니다."));

        List<Follows> following = followsRepository.findByFollowing(user);
        List<String> followingUserName = new ArrayList<>();

        for (Follows follow : following){
            //
            Users followingUser = follow.getFollowing();
            followingUserName.add(followingUser.getUserName());
        }

        return followingUserName;
    }

    //수정필요
    @Override
    public List<String> followerUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("팔로워한 유저가 없습니다."));

        List<Follows> follower = followsRepository.findByFollower(user);
        List<String> followerUserName = new ArrayList<>();

        for (Follows follow : follower){
            Users followergUser = follow.getFollower();
            followerUserName.add(followergUser.getUserName());
        }
        return followerUserName;
    }
}

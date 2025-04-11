package com.example.NewsFeed.service;

import com.example.NewsFeed.entity.Follows;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.FollowsRepository;
import com.example.NewsFeed.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FollowsServiceImpl implements FollowsService{


    private final UsersRepository usersRepository;
    private final FollowsRepository followsRepository;

    public FollowsServiceImpl(UsersRepository usersRepository , FollowsRepository followsRepository) {
        this.usersRepository = usersRepository;
        this.followsRepository = followsRepository;
    }

    // 이름 중복문제 해결해야함...
    //팔로잉 유저목록
    @Override
    public List<String> followingUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("팔로잉한 유저가 없습니다."));

        List<Follows> following = followsRepository.findByFollower(user);
        Set<Long> names = new HashSet<>();
        List<String> followingUserName = new ArrayList<>();

        for (Follows follow : following){
            Users followingUser = follow.getFollowing();
            if(names.add(followingUser.getId())){
                followingUserName.add(followingUser.getUserName());
            }
        }

        return followingUserName;
    }

    //팔로워 유저 목록
    @Override
    public List<String> followerUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("팔로워한 유저가 없습니다."));

        List<Follows> follower = followsRepository.findByFollowing(user);
        Set<Long> names = new HashSet<>();
        List<String> followerUserName = new ArrayList<>();

        for (Follows follow : follower){
            Users followerUser = follow.getFollower();
            if(names.add(follow.getId())){
                followerUserName.add(followerUser.getUserName());
            }
        }
        return followerUserName;
    }
}

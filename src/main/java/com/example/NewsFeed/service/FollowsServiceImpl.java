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
    public List<String> followUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        List<Follows> follower = followsRepository.findByFollower(user);
        List<String> followerUserName = new ArrayList<>();

        for (Follows follow : follower){
            //
            Users followingUser = follow.getFollowing();
            followerUserName.add(followingUser.getUserName());
        }

        return followerUserName;
    }
}

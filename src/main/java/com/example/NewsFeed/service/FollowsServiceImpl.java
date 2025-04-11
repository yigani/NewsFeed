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

    //팔로우
    @Override
    public void follow(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        Users fromUser = usersRepository.findById(fromUserId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Users toUser = usersRepository.findById(toUserId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        if (followsRepository.existsByFollowerAndFollowing(fromUser, toUser)){
            throw new IllegalArgumentException("이미 팔로우한 유저입니다.");
        }
        Follows follow = new Follows(fromUser, toUser);
        followsRepository.save(follow);
    }
    //언팔로우
    @Override
    public void unfollow(Long fromUserId, Long toUserId) {

        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("자기 자신을 언팔로우할 수 없습니다.");
        }

        Users fromUser = usersRepository.findById(fromUserId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Users toUser = usersRepository.findById(toUserId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Follows follow = followsRepository.findByFollowerAndFollowing(fromUser,toUser)
                .orElseThrow(()-> new IllegalArgumentException("팔로우 관계가 존재하지 않습니다."));

        followsRepository.delete(follow);
    }


    // 이름 중복문제 해결
    //팔로잉 유저목록
    @Override
    public List<String> followingUserNames(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

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
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        List<Follows> follower = followsRepository.findByFollowing(user);
        Set<Long> names = new HashSet<>();
        List<String> followerUserName = new ArrayList<>();

        for (Follows follow : follower){
            Users followerUser = follow.getFollower();
            if(names.add(followerUser.getId())){
                followerUserName.add(followerUser.getUserName());
            }
        }
        return followerUserName;
    }

    @Override
    public int followerCount(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        int count = followsRepository.countByFollower(users);
        return count;
    }

    @Override
    public int followingCount(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        int count = followsRepository.countByFollowing(users);
        return count;
    }

}

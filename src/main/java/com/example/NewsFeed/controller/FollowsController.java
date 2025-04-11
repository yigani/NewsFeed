package com.example.NewsFeed.controller;

import com.example.NewsFeed.repository.FollowsRepository;
import com.example.NewsFeed.service.FollowsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class FollowsController {

    private final FollowsRepository followsRepository;
    private final FollowsService followsService;

    public FollowsController(FollowsRepository followsRepository, FollowsService followsService) {
        this.followsRepository = followsRepository;
        this.followsService = followsService;
    }

    //팔로우
    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestParam Long fromUser,@RequestParam Long toUser){
        followsService.follow(fromUser,toUser);
        return new ResponseEntity<>("팔로우 성공",HttpStatus.OK);
    }

    //언팔
    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long fromUser,@RequestParam Long toUser){
        followsService.unfollow(fromUser,toUser);
        return new ResponseEntity<>("언팔 성공",HttpStatus.OK);
    }



    //목록조회
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<String>> followingList(@PathVariable Long userId){
        List<String> followingNameList = followsService.followingUserNames(userId);
        return new ResponseEntity<>(followingNameList,HttpStatus.OK);
    }

    @GetMapping("/{userId}/follower")
    public ResponseEntity<List<String>> followerList(@PathVariable Long userId){
        List<String> followerNameList = followsService.followerUserNames(userId);
        return new ResponseEntity<>(followerNameList,HttpStatus.OK);
    }

}

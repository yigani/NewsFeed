package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.follows.FollowRequestDto;
import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.entity.Users;
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
    public ResponseEntity<String> follow(@SessionAttribute(required = false, name = Const.LOGIN_USER) Users users, @RequestBody FollowRequestDto followRequestDto){
        followsService.follow(users.getId(),followRequestDto.getToUserId());
        return new ResponseEntity<>("팔로우 성공",HttpStatus.OK);
    }

    //로그인 기능 생기면 수정
    //언팔
    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(@SessionAttribute(required = false,name = Const.LOGIN_USER) Users users, @RequestBody FollowRequestDto followRequestDto){

        followsService.unfollow(users.getId(),followRequestDto.getToUserId());
        return new ResponseEntity<>("언팔 성공",HttpStatus.OK);
    }


    //목록조회
    @GetMapping("/{id}/following")
    public ResponseEntity<List<String>> followingList(@PathVariable Long id){
        List<String> followingNameList = followsService.followingUserNames(id);
        return new ResponseEntity<>(followingNameList,HttpStatus.OK);
    }

    @GetMapping("/{id}/follower")
    public ResponseEntity<List<String>> followerList(@PathVariable Long id){
        List<String> followerNameList = followsService.followerUserNames(id);
        return new ResponseEntity<>(followerNameList,HttpStatus.OK);
    }

    //카운트
    @GetMapping("/{id}/followercount")
    public ResponseEntity<Integer> followerCount(@PathVariable Long id) {
        int count = followsService.followerCount(id);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @GetMapping("/{id}/followingcount")
    public ResponseEntity<Integer> followingCount(@PathVariable Long id) {
        int count = followsService.followingCount(id);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

}


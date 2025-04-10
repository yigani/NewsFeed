package com.example.NewsFeed.controller;

import com.example.NewsFeed.repository.FollowsRepository;
import com.example.NewsFeed.service.FollowsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<String>> followingList(@PathVariable Long userId){
        List<String> followingNameList = followsService.followUserNames(userId);
        return new ResponseEntity<>(followingNameList,HttpStatus.OK);
    }


}

package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    //
    @GetMapping("/info/me")
    public ResponseEntity<UserResponseDto> myUserInfo(@RequestParam Long userId){
        UserResponseDto userResponseDto = usersService.myUserInfo(userId);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserInfoResponseDto> userInfo(@PathVariable Long userId){
        UserInfoResponseDto userInfoResponseDto = usersService.userInfo(userId);
        return new ResponseEntity<>(userInfoResponseDto,HttpStatus.OK);
    }
}

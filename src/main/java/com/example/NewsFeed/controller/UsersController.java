package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;
import com.example.NewsFeed.dto.users.UserSearchResponseDto;
import com.example.NewsFeed.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;


    // 로그인 유저 정보 조회
    @GetMapping("/info/me")
    public ResponseEntity<UserResponseDto> myUserInfo(@RequestParam Long id) {
        UserResponseDto userResponseDto = usersService.myUserInfo(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 특정 유저 이름 조회
    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfoResponseDto> userInfo(@PathVariable Long id) {
        UserInfoResponseDto userInfoResponseDto = usersService.userInfo(id);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    //유저 검색
    @GetMapping
    public ResponseEntity<List<UserSearchResponseDto>> searchUser(@RequestParam("search") String name) {

        List<UserSearchResponseDto> searchUser = usersService.searchUser(name);

        return new ResponseEntity<>(searchUser, HttpStatus.OK);
    }

}

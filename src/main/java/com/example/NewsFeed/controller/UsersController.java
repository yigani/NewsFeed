package com.example.NewsFeed.controller;

import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.service.UsersService;
import jakarta.validation.Valid;
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

    /**
     * 회원 가입(유저 생성)
     */
    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(@Valid @RequestBody SignUpUserRequestDto dto) {

        SignUpUserResponseDto signUpUser = usersService.signUp(dto);

        return new ResponseEntity<>(signUpUser, HttpStatus.CREATED);
    }

    /**
     * 프로필 생성
     */
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@RequestParam Long id, @Valid @RequestBody CreateProfileRequestDto dto) {

        CreateProfileResponseDto createdProfile = usersService.createProfile(id, dto);

        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    /**
     * 비밀번호 변경
     */
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestParam Long id, @Valid @RequestBody UpdatePasswordRequestDto dto) {

        usersService.updatePassword(id, dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 회원 탈퇴
     */
    @PatchMapping("/me/status")
    public ResponseEntity<DeactivateUserResponseDto> deactivateUser(@RequestParam Long id, @Valid @RequestBody DeactivateUserRequestDto dto) {

        DeactivateUserResponseDto deactivateUserResponseDto = usersService.deactivateUser(id, dto);

        return new ResponseEntity<>(deactivateUserResponseDto, HttpStatus.OK);
    }



    // TODO 로그인 구현 후 수정 필요
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

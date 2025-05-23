package com.example.NewsFeed.controller;

import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // 회원 가입 (유저 생성)
    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signUp(@Valid @RequestBody SignUpUserRequestDto dto) {

        SignUpUserResponseDto signUpUser = usersService.signUp(dto);

        return new ResponseEntity<>(signUpUser, HttpStatus.CREATED);
    }

    // 로그인 된 유저 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile(@SessionAttribute(name = Const.LOGIN_USER) Long id, @Valid @RequestBody CreateProfileRequestDto dto) {

        CreateProfileResponseDto createdProfile = usersService.createProfile(id, dto);

        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // 로그인 된 유저 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@SessionAttribute(name = Const.LOGIN_USER) Users users, @Valid @RequestBody UpdatePasswordRequestDto dto) {

        usersService.updatePassword(users.getId(), dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인 된 유저 탈퇴
    @PatchMapping("/me/status")
    public ResponseEntity<DeactivateUserResponseDto> deactivateUser(@SessionAttribute(name = Const.LOGIN_USER) Long id, @Valid @RequestBody DeactivateUserRequestDto dto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        // session이 존재하지 않는다면 세션을 만료시킨다.
        if (session != null) {
            session.invalidate();
        }

        DeactivateUserResponseDto deactivateUserResponseDto = usersService.deactivateUser(id, dto);

        return new ResponseEntity<>(deactivateUserResponseDto, HttpStatus.OK);
    }

    // 로그인 된 유저 이름, 이메일(민감한 정보) 조회
    @GetMapping("/info/me")
    public ResponseEntity<UserResponseDto> myUserInfo(@SessionAttribute(name = Const.LOGIN_USER) Long id) {
        UserResponseDto userResponseDto = usersService.myUserInfo(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // id로 유저 이름 조회
    @GetMapping("/info/{id}")
    public ResponseEntity<UserInfoResponseDto> userInfo(@PathVariable Long id) {
        UserInfoResponseDto userInfoResponseDto = usersService.userInfo(id);
        return new ResponseEntity<>(userInfoResponseDto, HttpStatus.OK);
    }

    // 유저명으로 유저 검색
    @GetMapping
    public ResponseEntity<List<UserSearchResponseDto>> searchUser(@RequestParam("search") String name) {

        List<UserSearchResponseDto> searchUser = usersService.searchUser(name);

        return new ResponseEntity<>(searchUser, HttpStatus.OK);
    }
}

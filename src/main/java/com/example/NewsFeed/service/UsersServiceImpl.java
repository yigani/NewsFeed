package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.ProfileResponseDto;
import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;

    @Override
    public UserResponseDto myUserInfo(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return new UserResponseDto(user.getUserName(),user.getEmail());
    }

    @Override
    public UserInfoResponseDto userInfo(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new  IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        return new  UserInfoResponseDto(user.getUserName());
    }
}

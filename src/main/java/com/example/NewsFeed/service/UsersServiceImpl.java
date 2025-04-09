package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserResponseDto;
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
    public SignUpUserResponseDto signUp(SignUpUserRequestDto dto) {

        Users saveduser = new Users(dto);
        usersRepository.save(saveduser);
        
        return new SignUpUserResponseDto(saveduser);
    }
}

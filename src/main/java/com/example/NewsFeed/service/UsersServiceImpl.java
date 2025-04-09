package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.posts.P;
import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public CreateProfileResponseDto createProfile(CreateProfileRequestDto dto) {

        Profiles savedprofile = new Profiles(dto);
        profilesRepository.save(savedprofile);

        return new CreateProfileResponseDto(savedprofile);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        findUser.updatePassword(dto);

    }
}

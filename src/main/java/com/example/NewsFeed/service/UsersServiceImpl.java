package com.example.NewsFeed.service;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;


    @Override
    public SignUpUserResponseDto signUp(SignUpUserRequestDto dto) {

        Users savedUser = new Users(dto);
        usersRepository.save(savedUser);

        return new SignUpUserResponseDto(savedUser);
    }

    @Override
    public CreateProfileResponseDto createProfile(Long id, CreateProfileRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        Profiles savedProfile = new Profiles(findUser, dto);
        profilesRepository.save(savedProfile);

        return new CreateProfileResponseDto(savedProfile);
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

    @Override
    @Transactional
    public DeactivateUserResponseDto deactivateUser(Long id, DeactivateUserRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(dto.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        findUser.deactivateUser();

        return new DeactivateUserResponseDto(findUser);
    }

}

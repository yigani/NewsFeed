package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.UserInfoResponseDto;
import com.example.NewsFeed.dto.users.UserResponseDto;
import com.example.NewsFeed.dto.users.UserSearchResponseDto;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;
    //TODO 확인 필요
    private final PasswordEncoder passwordEncoder;

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

        if(dto.getPassword().equals(dto.getNewPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

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

    @Override
    public List<UserSearchResponseDto> searchUser(String name) {

        List<Users> searchUsers = usersRepository.findByUserNameContaining(name);
        List<UserSearchResponseDto> result = new ArrayList<>();

        for (Users users : searchUsers){
            result.add(new UserSearchResponseDto(users.getId(), users.getUserName()));
        }

        return result;
    }
}

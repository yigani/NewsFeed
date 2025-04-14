package com.example.NewsFeed.service;

import com.example.NewsFeed.consts.Const;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.exception.DeletedUserException;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;

    public ProfileServiceImpl(UsersRepository usersRepository, ProfilesRepository profilesRepository) {
        this.usersRepository = usersRepository;
        this.profilesRepository = profilesRepository;
    }

    // id로 유저 프로필 조회
    @Override
    public ProfileResponseDto userProfile(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        if (user.isDelete()) {
            throw new DeletedUserException("해당 유저가 존재하지 않습니다.");
        }

        Profiles profiles = profilesRepository.findByUserId(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        return new ProfileResponseDto(
                profiles.getGender(),
                profiles.getBirthday(),
                profiles.getIntroduction(),
                profiles.getImage());
    }

    // 로그인 된 유저의 프로필 수정
    @Override
    @Transactional
    public MyProfileUpdateResponseDto updateProfile(@SessionAttribute(name = Const.LOGIN_USER) Long userId, MyProfileUpdateRequestDto requestDto) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        Profiles profile = profilesRepository.findByUserId(user)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        user.updateUserName(requestDto.getUsers().getUsername());

        profile.updateProfile(
                requestDto.getProfiles().getGender(),
                requestDto.getProfiles().getIntroduction(),
                requestDto.getProfiles().getImage(),
                requestDto.getProfiles().getBirthday()
        );

        MyProfileUpdateResponseDto.UserName userName = new MyProfileUpdateResponseDto.UserName(user.getUserName());
        MyProfileUpdateResponseDto.UserProfile userProfile = new MyProfileUpdateResponseDto.UserProfile(profile.getGender(), profile.getBirthday(), profile.getIntroduction(), profile.getImage());

        return new MyProfileUpdateResponseDto(userName, userProfile);
    }
}

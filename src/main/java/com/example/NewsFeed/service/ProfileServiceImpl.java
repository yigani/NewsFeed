package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.profiles.MyProfileUpdateRequestDto;
import com.example.NewsFeed.dto.profiles.MyProfileUpdateResponseDto;
import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;

    public ProfileServiceImpl(UsersRepository usersRepository, ProfilesRepository profilesRepository) {
        this.usersRepository = usersRepository;
        this.profilesRepository = profilesRepository;
    }

    @Override
    public ProfileResponseDto userProfile(Long userId) {

        Profiles profiles = profilesRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        return new ProfileResponseDto(
                profiles.getGender(),
                profiles.getBirthday(),
                profiles.getIntroduction(),
                profiles.getImage());
    }

    @Override
    public MyProfileUpdateResponseDto updateProfile(Long userId, MyProfileUpdateRequestDto requestDto) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        Profiles profile = profilesRepository.findById(userId)
                        .orElseThrow(()-> new IllegalArgumentException("프로필이 생성되지 않았습니다."));

        user.updateUserName(requestDto.getUsers().getUsername());

        profile.updateProfile(
                requestDto.getProfiles().getGender(),
                requestDto.getProfiles().getIntroduction(),
                requestDto.getProfiles().getImage(),
                requestDto.getProfiles().getBirthday()
        );

        // TODO 저장 부분 수정 진행 예정
        usersRepository.save(user);
        profilesRepository.save(profile);

        MyProfileUpdateResponseDto.UserName userName = new MyProfileUpdateResponseDto.UserName(user.getUserName());
        MyProfileUpdateResponseDto.UserProfile userProfile = new MyProfileUpdateResponseDto.UserProfile(profile.getGender(), profile.getBirthday(), profile.getIntroduction(),profile.getImage());

        MyProfileUpdateResponseDto responseDto = new MyProfileUpdateResponseDto(userName,userProfile);

        return responseDto;
    }
}

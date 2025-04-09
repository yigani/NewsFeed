package com.example.NewsFeed.service;

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
}

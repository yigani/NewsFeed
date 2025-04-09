package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.profiles.ProfileResponseDto;
import com.example.NewsFeed.repository.ProfilesRepository;
import org.apache.catalina.User;

public interface ProfileService {

    ProfileResponseDto userProfile(Long userId);

}

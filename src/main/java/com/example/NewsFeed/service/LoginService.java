package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.login.LoginRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    Long handleLogin(LoginRequestDto dto);
}

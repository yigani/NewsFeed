package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.login.LoginRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    Long handleLogin(LoginRequestDto dto);

}

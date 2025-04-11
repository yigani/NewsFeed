package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.login.LoginRequestDto;
import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public Long handleLogin(LoginRequestDto dto);
    public String passwordEncrypt(SignUpUserRequestDto dto);

}

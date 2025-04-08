package com.example.NewsFeed.service;

import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

}

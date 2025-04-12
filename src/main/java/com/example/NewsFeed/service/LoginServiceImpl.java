package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.login.LoginRequestDto;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.exception.InvalidCredentialException;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // 입력된 ID(이메일) 존재 여부 확인
    // 입력된 비밀번호 일치 여부 확인
    @Override
    @Transactional(readOnly = true)
    public Long handleLogin(LoginRequestDto dto) {
        Users user = usersRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new InvalidCredentialException("해당 아이디가 존재하지 않습니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }
}

package com.example.NewsFeed.service;

import com.example.NewsFeed.dto.users.*;
import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.ProfilesRepository;
import com.example.NewsFeed.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ProfilesRepository profilesRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입 (유저 생성)
    @Override
    public SignUpUserResponseDto signUp(SignUpUserRequestDto dto) {

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Users savedUser = new Users(dto.getEmail(), encodedPassword, dto.getUsername());
        usersRepository.save(savedUser);

        return new SignUpUserResponseDto(savedUser);
    }

    // 로그인 유저의 프로필 생성
    @Override
    public CreateProfileResponseDto createProfile(Long id, CreateProfileRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        Profiles savedProfile = new Profiles(findUser, dto);
        profilesRepository.save(savedProfile);

        return new CreateProfileResponseDto(savedProfile);
    }

    // 로그인 유저의 비밀번호 변경
    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        String encodedNewPassword = passwordEncoder.encode(dto.getNewPassword());

        if (dto.getPassword().equals(dto.getNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        findUser.updatePassword(encodedNewPassword);
    }

    // 로그인된 유저 탈퇴
    @Override
    @Transactional
    public DeactivateUserResponseDto deactivateUser(Long id, DeactivateUserRequestDto dto) {

        Users findUser = usersRepository.findUsersByIdOrElseThrow(id);

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        findUser.deactivateUser();

        return new DeactivateUserResponseDto(findUser);
    }

    // 로그인 유저의 정보 조회
    @Override
    public UserResponseDto myUserInfo(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return new UserResponseDto(user.getUserName(), user.getEmail());
    }

    // id로 유저의 이름 조회
    @Override
    public UserInfoResponseDto userInfo(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        if (user.isDelete()) {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        }

        return new UserInfoResponseDto(user.getUserName());
    }

    // 이름으로 유저 검색
    @Override
    public List<UserSearchResponseDto> searchUser(String name) {

        List<Users> searchUsers = usersRepository.findByUserNameContaining(name);
        List<UserSearchResponseDto> result = new ArrayList<>();

        for (Users users : searchUsers) {
            result.add(new UserSearchResponseDto(users.getId(), users.getUserName()));
        }

        return result;
    }
}

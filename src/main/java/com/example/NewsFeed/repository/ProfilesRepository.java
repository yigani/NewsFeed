package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Profiles;
import com.example.NewsFeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {

    Optional<Profiles> findByUserId(Users user);
}

package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
}

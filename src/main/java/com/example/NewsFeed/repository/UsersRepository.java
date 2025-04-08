package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}

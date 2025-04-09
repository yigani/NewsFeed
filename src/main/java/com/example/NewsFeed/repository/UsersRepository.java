package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {

    List<Users> findByUserNameContaining(String userName);

}

package com.example.NewsFeed.repository;

import com.example.NewsFeed.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {

    List<Users> findByUserNameContaining(String userName);

    default Users findUsersByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotNull
    private boolean isDelete;

    public Users(SignUpUserRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.userName = dto.getUsername();
        this.isDelete = false;
    }
}
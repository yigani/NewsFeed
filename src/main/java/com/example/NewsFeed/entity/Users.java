package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String userName;

    private boolean isDelete;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    private List<Follows> followings;

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY)
    private List<Follows> followers;

    public Users(SignUpUserRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.userName = dto.getUsername();
        this.isDelete = false;
    }

    public Users(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.isDelete = false; // 기본값으로 false 설정
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void deactivateUser() {
        this.isDelete = true;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }
}
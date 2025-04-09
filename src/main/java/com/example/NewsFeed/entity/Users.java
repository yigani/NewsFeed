package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.users.SignUpUserRequestDto;
import com.example.NewsFeed.dto.users.UpdatePasswordRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity{

    //Todo id랑 email은 final붙여야 하지 않을까요!?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    private String password;

    @NotBlank
    @Size(min = 1, max = 30)
    private String userName;

    @NotNull
    private boolean isDelete;

    public Users(SignUpUserRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.userName = dto.getUsername();
        this.isDelete = false;
    }

    public void updatePassword(UpdatePasswordRequestDto dto) {
        this.password = dto.getNewPassword();
    }
}
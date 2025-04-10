package com.example.NewsFeed.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Size(min = 1, max = 100)
    private String password;

    @NotBlank
    @Size(min = 1, max = 30)
    private String userName;

    @NotNull
    private boolean isDelete;

    public void updateUserName(String userName){
        this.userName = userName;
    }

}
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

    public void updateUserName(String userName){
        this.userName = userName;
    }

}
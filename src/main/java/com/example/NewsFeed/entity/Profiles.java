package com.example.NewsFeed.entity;

import com.example.NewsFeed.dto.users.CreateProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "profiles")
public class Profiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;

    private String gender;

    private String introduction;

    private String image;

    private LocalDate birthday;

    public Profiles(CreateProfileRequestDto dto) {
        this.gender = dto.getGender();
        this.introduction = dto.getIntroduction();
        this.image = dto.getImage();
        this.birthday = dto.getBirthday();
    }
}

package com.example.NewsFeed.entity;

import com.example.NewsFeed.config.Gender;
import com.example.NewsFeed.dto.users.CreateProfileRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    // TODO 추후 입력받는 값에 따라 수정 가능성 있음
//    @Size(max = 20)
//    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(max = 100)
    @Column(length = 100)
    private String introduction;

    @Size(max = 255)
    @Column
    private String image;

    private LocalDate birthday;
    
    public Profiles(Users users, CreateProfileRequestDto dto) {
        this.gender = Gender.fromInt(dto.getGender());
        this.introduction = dto.getIntroduction();
        this.image = dto.getImage();
        this.birthday = dto.getBirthday();
        this.userId = users;
    }

    public void updateProfile(int gender,String introduction,String image, LocalDate birthday){
        this.gender = Gender.fromInt(gender);
        this.introduction = introduction;
        this.image = image;
        this.birthday = birthday;

    }
}

package com.example.NewsFeed.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Size(max = 20)
    private String gender;

    @Size(max = 100)
    private String introduction;

    @Size(max = 255)
    private String image;

    private LocalDateTime birthday;
}

package com.example.NewsFeed.dto.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostsRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 30, message = "제목은 1 글자 이상 30 글자 이하로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 100, message = "내용은 1 글자 이상 100 글자 이하로 입력해주세요.")
    private String contents;
}

package com.example.NewsFeed;

import com.example.NewsFeed.dto.posts.CreatePostsRequestDto;
import com.example.NewsFeed.entity.Users;
import com.example.NewsFeed.repository.UsersRepository;
import com.example.NewsFeed.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//구글에서 긁어온 테스트용 더미 포스트 생성기

@SpringBootTest
class DummyDataGeneratorTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void generateDummyPosts() {
        // 테스트 유저 생성 (없으면 새로 생성)
        Users testUser = usersRepository.findById(1L).orElseGet(() -> {
            Users newUser = new Users("test@example.com", "1234", "테스트유저");
            return usersRepository.save(newUser);
        });

        for (int i = 1; i <= 300; i++) {
            String title = String.format("더미 게시글 제목 [%03d]", i);
            String contents = "이것은 더미 내용입니다.";
            CreatePostsRequestDto dto = new CreatePostsRequestDto(title, contents);
            postsService.create(dto, testUser);
        }
    }
}
//커밋 확인용
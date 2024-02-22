package com.springboot.aws.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("narsong@gmail.com")
                                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2024,2,19,0,0,0);
        postsRepository.save(Posts.builder()
                                    .title("title")
                                    .content("content")
                                    .author("narsong@gmail.com")
                                    .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>> createDate = " + posts.getCreatedDate() + ", modifiedDate = " + posts.getModifiedDate());
        System.out.println(">>>>>>>>>>>> now = " + now);
        assertThat(posts.getCreatedDate()).isEqualTo(now);
        assertThat(posts.getModifiedDate()).isEqualTo(now);
    }
}

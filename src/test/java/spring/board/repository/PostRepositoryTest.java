package spring.board.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Post;
import spring.board.domain.Role;
import spring.board.domain.User;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Test
    @Transactional
    @Rollback(value = true)
    void save테스트(){
        User user1 = new User();

        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("title1");
        post1.setContent("hello!");
        post1.setUser(user1);

        Post findPost = postRepository.save(post1);
        Assertions.assertEquals(post1,findPost);


    }
    @Test
    @Transactional
    @Rollback(value = true)
    void delete테스트(){
        User user =User.oauth2Register()
                .username("username1").password("password1").email("email").role(Role.ROLE_GUEST)
                .provider("provider").providerId("providerId")
                .build();
        Post post = new Post();
        post.setUser(user);
        post.setTitle("title1");
        post.setContent("content1");
        postRepository.save(post);
        postRepository.deleteById(post.getId());
        Assertions.assertEquals(1,postRepository.findAll().size());

    }
}
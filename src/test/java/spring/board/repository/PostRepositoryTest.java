package spring.board.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Post;
import spring.board.domain.Role;
import spring.board.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Test
    @Transactional
    @Rollback(value = true)
    public void save테스트(){
        User user1 = new User();
        user1.setName("name1");
        user1.setEmail("email1");
        user1.setPicture("picture");
        user1.setRole(Role.USER);
        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setContent("hello!");
        post1.setUser(user1);

        postRepository.save(post1);
        List<Post> findPosts = postRepository.findByUser(user1.getId());
        assertThat(findPosts.size()).isEqualTo(1);

    }
}
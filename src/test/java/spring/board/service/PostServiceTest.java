package spring.board.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spring.board.domain.Post;
import spring.board.domain.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    @Rollback(value = true)
    void 게시물저장(){
        User user1 = new User();
        Post post1 = new Post();
        post1.setTitle("title1");
        post1.setContent("hello!");
        post1.setUser(user1);

        postService.savePost(post1);
    }

}

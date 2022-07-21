package spring.board.Controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void 게시물생성페이지로딩(){
        String body = restTemplate.getForObject("/posts/new", String.class);

        //then
        Assertions.assertThat(body).contains("게시물생성페이지");
    }
}

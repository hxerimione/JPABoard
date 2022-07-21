package spring.board.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void 로그인페이지로딩() {
        //when
        String body = restTemplate.getForObject("/login", String.class);

        //then
        assertThat(body).contains("로그인페이지");
    }


}

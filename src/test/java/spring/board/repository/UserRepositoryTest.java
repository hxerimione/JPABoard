package spring.board.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Role;
import spring.board.domain.User;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    @Transactional
    @Rollback(value = true)
    void findAll테스트 () throws Exception{
        User user = new User();
        user =  User.oauth2Register()
                .username("name1")
                .password("password1")
                .email("email1@daum.net")
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        Assertions.assertEquals(1,userRepository.findAll().size());
    }

    @Transactional
    @Rollback(value = true)
    @Test
    void findById() {

    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }
}
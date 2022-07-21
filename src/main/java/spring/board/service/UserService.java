package spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Post;
import spring.board.domain.User;
import spring.board.repository.PostRepositoryClass;
import spring.board.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepositoryClass postRepositoryClass;
    @Transactional
    public void join(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();

    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
    public void validateDuplicateMember(User user){
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(!findUsers.isEmpty()){
            //System.out.println("이미 존재하는 회원");
            throw new IllegalStateException("이미 존재하는 회원입니다");
            //이 에러때문에 controller에서 에러처리가 안되는데 어떻게 하지
        }
    }
    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<Post> findPosts(Long userId) {
        return postRepositoryClass.findByUserId(userId);
    }
}

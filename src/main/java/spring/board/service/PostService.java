package spring.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.board.domain.Post;
import spring.board.domain.User;
import spring.board.repository.PostRepository;
import spring.board.repository.PostRepositoryClass;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostRepositoryClass postRepositoryClass;

    @Transactional
    public void savePost(Post post){
        postRepository.save(post);
    }

    @Transactional
    public Post findById(Long id){
        return postRepository.findById(id).get();
    }
    @Transactional
    public List<Post> findByUser(Long userId){
        return postRepositoryClass.findByUserId(userId);
    }

    @Transactional
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    @Transactional
    public void update(Post post){
        postRepository.update(post.getTitle(),post.getContent(),post.getId());
    }
//    public List<PostDto> findAllDto(){
//        return postRepository.findAllDto();
//    }
//    public void savePostDto(PostDto postDto){
//        postRepository.save(postDto);
//    }
    @Transactional
    public void deleteById(Long postId){
        postRepository.deleteById(postId);
    }
}
package spring.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.board.domain.Post;
import spring.board.domain.User;
import spring.board.dto.PostDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Post post){
        em.persist(post);
    }
//    public void save(PostDto postDto){
//        em.persist(postDto);
//    }
    public Post findById(Long id){
        return em.find(Post.class,id);
    }
    public List<Post> findByUserId(Long userId){
        return em.createQuery("select p from Post p where p.user.id=:userId")
                .setParameter("userId",userId)
                .getResultList();
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p").getResultList();
    }
//    public List<PostDto> findAllDto(){
//        return em.createQuery("select p from Post p").getResultList();
//    }
}
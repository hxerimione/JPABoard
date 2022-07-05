package spring.board.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.domain.Post;
import spring.board.domain.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
//    @PersistenceContext
//    EntityManager em;
//
//    public void save(Post post){
//        em.persist(post);
//    }
////    public void save(PostDto postDto){
////        em.persist(postDto);
////    }
//    public Post findById(Long id){
//        return em.find(Post.class,id);
//    }
//    public List<Post> findByUserId(Long userId){
//        return em.createQuery("select p from Post p where p.user.id=:userId")
//                .setParameter("userId",userId)
//                .getResultList();
//    }
//
//    public List<Post> findAll(){
//        return em.createQuery("select p from Post p").getResultList();
//    }
////    public List<PostDto> findAllDto(){
////        return em.createQuery("select p from Post p").getResultList();
////    }
    @Override
    List<Post> findAll();

    @Override
    <S extends Post> S save(S entity);

}
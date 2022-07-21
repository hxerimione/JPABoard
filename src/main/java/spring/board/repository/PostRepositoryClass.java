package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class PostRepositoryClass  {
    @PersistenceContext
    EntityManager em;

    public List<Post> findByUserId(Long userId){
        return em.createQuery("select p from Post p where p.user.id=:userId")
                .setParameter("userId",userId)
                .getResultList();
    }



}

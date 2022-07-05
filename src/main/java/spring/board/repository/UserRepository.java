package spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.board.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
//    @PersistenceContext
//    EntityManager em;
//
//    public void save(User user){
//        em.persist(user);
//    }
//    public User findById(Long id){
//        return em.find(User.class,id);
//    }
//    public List<User> findByEmail(String email){
//        return em.createQuery("select u from User u where u.email =: email",User.class)
//                .setParameter("email",email)
//                .getResultList();
//    }
//    public List<User> findAll(){
//        return em.createQuery("select u from User u",User.class).getResultList();
//    }
//
//    public User findByUsername(String username){
//        User user = null;
//        try {
//            user = em.createQuery("select u from User u where u.username =: username", User.class)
//                    .setParameter("username", username)
//                    .getSingleResult();
//        }catch (NoResultException e){
//            System.out.println("###" + e);
//        }
//        finally {
//            return user;
//        }
//    }


    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);

    public Optional<User> findById(Long id);

    User findByUsername(String username);
    List<User> findByEmail(String email);
}

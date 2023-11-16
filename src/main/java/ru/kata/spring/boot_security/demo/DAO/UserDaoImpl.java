package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    @Override
    public User showUser(Long id) {
        return entityManager.find(User.class,id);
    }
    @Override
    public void saveUser(User user) {
        if(user.getId() != 0) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
        entityManager.flush();
    }
    @Override
    public void delUser(Long id) {
        entityManager.remove(showUser(id));
        entityManager.flush();
    }

    @Override
    public User findByUsername(String name) {
        return entityManager.createQuery(
                "SELECT user FROM User user join fetch  user.roles WHERE user.name =:name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }


}

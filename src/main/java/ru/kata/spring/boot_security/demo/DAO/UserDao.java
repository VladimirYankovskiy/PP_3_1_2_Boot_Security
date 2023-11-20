package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> allUsers();

    User showUser(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void delUser(Long id);

    User findByUsername(String name);
}

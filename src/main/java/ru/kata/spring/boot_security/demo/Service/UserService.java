package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User showUser(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void delUser(Long id);

    User findByUsername(String name);
}

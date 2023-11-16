package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String role);
    List<Role> getSetOfRoles(String[] roleNames);
    void addRole(Role role);
    void editRole(Role role);
    Role getById(Long id);
}

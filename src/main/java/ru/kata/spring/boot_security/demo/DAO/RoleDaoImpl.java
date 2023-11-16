package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r",Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery("SELECT r from Role r where r.role=:role",Role.class)
                .setParameter("role",role).getSingleResult();
    }

    @Override
    public List<Role> getSetOfRoles(String[] roleNames) {
        List<Role> list = new ArrayList<>();
        for (String role: roleNames) {
            list.add(getRoleByName(role));
        }
        return list;
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
        entityManager.flush();
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
        entityManager.flush();
    }

    @Override
    public Role getById(Long id) {
        return entityManager.createQuery("SELECT r from Role r where r.id=:id",Role.class)
                .setParameter("id",id).getSingleResult();
    }
}

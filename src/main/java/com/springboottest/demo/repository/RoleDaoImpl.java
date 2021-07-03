package com.springboottest.demo.repository;

import com.springboottest.demo.entity.Role;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        TypedQuery<Role> userTypedQuery = entityManager.createQuery("select  u from Role u where u.id = : id", Role.class);
        userTypedQuery.setParameter("id", id);

        return userTypedQuery.getResultList().stream().findAny().orElse(null);
    }
}

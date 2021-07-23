package com.springboottest.demo.repository;

import com.springboottest.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class RoleDaoImpl  {


    @Autowired
    private RoleDao roleDao;


    @PersistenceContext
    private EntityManager entityManager;


    public Role getById(Long id) {
        TypedQuery<Role> userTypedQuery = entityManager.createQuery("select  u from Role u where u.id = : id", Role.class);
        userTypedQuery.setParameter("id", id);

        return userTypedQuery.getResultList().stream().findAny().orElse(null);
    }
}

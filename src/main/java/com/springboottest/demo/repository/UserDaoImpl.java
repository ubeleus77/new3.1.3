package com.springboottest.demo.repository;

import com.springboottest.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();

    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        entityManager.createQuery("delete from User u where u.id = : id")
                .setParameter("id", id)
                .executeUpdate();

    }



    @Override
    public User getUserById(Long id) {
        TypedQuery<User> userTypedQuery = entityManager.createQuery("select  u from User u where u.id = : id", User.class);
        userTypedQuery.setParameter("id", id);
        return userTypedQuery.getResultList().stream().findAny().orElse(null);



    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getUserByName(String s) {
        TypedQuery<User> userTypedQuery = entityManager.createQuery("select  u from User u where u.firstName = : name", User.class);
        userTypedQuery.setParameter("name", s);
        return userTypedQuery.getResultList().stream().findAny().orElse(null);

    }


}

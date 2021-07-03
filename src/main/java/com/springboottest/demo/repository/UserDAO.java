package com.springboottest.demo.repository;


import com.springboottest.demo.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    List<User> getAllUsers();

     void removeUserById(Long id);

    User getUserById(Long id);

    void updateUser(User user);

    User getUserByName(String s);
}

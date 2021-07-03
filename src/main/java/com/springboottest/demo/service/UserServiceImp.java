package com.springboottest.demo.service;

import com.springboottest.demo.entity.Role;
import com.springboottest.demo.entity.User;
import com.springboottest.demo.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDAO userDAO;

    public UserServiceImp() {
    }

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserByName(String s) {
        return userDAO.getUserByName(s);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(name);
        if(user == null){
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));}

            return new org.springframework.security.core.userdetails.User(
                    user.getFirstName(), user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r ->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

}

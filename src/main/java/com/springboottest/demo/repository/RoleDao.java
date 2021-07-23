package com.springboottest.demo.repository;


import com.springboottest.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao  extends JpaRepository<Role, Long> {
   Role getById(Long id);
}

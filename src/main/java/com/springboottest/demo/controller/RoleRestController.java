package com.springboottest.demo.controller;

import com.springboottest.demo.entity.Role;
import com.springboottest.demo.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/users/roles")
public class RoleRestController {
    private final RoleDao roleRepository;

    @Autowired
    public RoleRestController(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Role>> showUsers() {
        List<Role> list = roleRepository.findAll();

        return !list.isEmpty() ?
                 new ResponseEntity<>(list, HttpStatus.OK) :
                 new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

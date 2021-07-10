package com.springboottest.demo.controller;

import com.springboottest.demo.entity.Role;
import com.springboottest.demo.entity.User;
import com.springboottest.demo.repository.RoleDao;
import com.springboottest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class AdminController {
    private final UserService userService;
    private final RoleDao roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleDao roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String show(Model model, Authentication aut) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userAuth", userService.getUserByEmail(aut.getName()));
        model.addAttribute("users", userList);
        return "demoIndex";
    }

    @PostMapping("/admin/save")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesId") List<String> roles) {
        Long parseLong = Long.parseLong(roles.get(0));
        if (parseLong != 1) {
            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add(roleRepository.getById(1L));
            rolesUser.add(roleRepository.getById(parseLong));
            user.setRoles(rolesUser);
        } else {
            user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete")
    public String delete(Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    @ResponseBody
    public User edit(Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/admin/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesId") List<String> roles) {
        Long parseLong = Long.parseLong(roles.get(0));
        if (parseLong != 1) {
            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add(roleRepository.getById(1L));
            rolesUser.add(roleRepository.getById(parseLong));
            user.setRoles(rolesUser);
        } else {
            user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }
}

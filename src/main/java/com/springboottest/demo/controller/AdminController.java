package com.springboottest.demo.controller;


import com.springboottest.demo.entity.Role;
import com.springboottest.demo.entity.User;
import com.springboottest.demo.repository.RoleDao;

import com.springboottest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleRepository;



    @GetMapping()
    public String getAllUsers(Model model){
        model.addAttribute("getAllUsers", userService.getAllUsers());
        return "testIndex";
    }

    @GetMapping("/new")
    public String createNewUser(Model model){
        model.addAttribute("createNewUserGetMethod", new User());
        return "testNew";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolesId") List<String> roles) {
        Long role = Long.parseLong(roles.get(0));
        if (role != 1L) {
            Set<Role> r1 = new HashSet<>();
            r1.add(roleRepository.getById(1L));
            r1.add(roleRepository.getById(role));
            user.setRoles(r1);
        } else {
            user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        }
        userService.addUser(user);
        return "redirect:/admin";
    }



    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "testEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id,
                         @RequestParam(value = "rolesId") List<String> roles) {
        Long role = Long.parseLong(roles.get(0));
        if (role != 1L) {
            Set<Role> r1 = new HashSet<>();
            r1.add(roleRepository.getById(1L));
            r1.add(roleRepository.getById(role));
            user.setRoles(r1);
        } else {
            user.setRoles(Collections.singleton(roleRepository.getById(1L)));
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }




}

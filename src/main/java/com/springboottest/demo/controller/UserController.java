package com.springboottest.demo.controller;

import com.springboottest.demo.entity.User;
import com.springboottest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index() {
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String showProfile(Model model,
                              Authentication aut) {
        User user = userService.getUserByEmail(aut.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model,
                        Authentication aut) {
        User user = userService.getUserByEmail(aut.getName());
        model.addAttribute("userAuthentication", user);
        return "show";
    }
}

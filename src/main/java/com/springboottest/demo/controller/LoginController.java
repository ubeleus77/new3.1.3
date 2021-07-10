package com.springboottest.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String red(){
        return "redirect:/admin";
    }


    @GetMapping("/login")
    public String log(){
        return "login";
    }

}
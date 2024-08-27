package com.example.SpringMVCSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login";
    }
}

package com.example.SpringMVCSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class errorController {

    @GetMapping("/show403")
    public String show403() {
        return "error/error403";
    }
}

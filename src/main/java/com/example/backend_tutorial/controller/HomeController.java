package com.example.backend_tutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "Home Page";
    }
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}

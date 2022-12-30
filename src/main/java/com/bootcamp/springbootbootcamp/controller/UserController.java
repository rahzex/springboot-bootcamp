package com.bootcamp.springbootbootcamp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @GetMapping("/home")
    public String home() {
        return "THIS IS HOME PAGE";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminHome() {
        return "THIS IS ADMIN HOME PAGE";
    }

    @GetMapping("/details")
    public String details() {
        return "THIS IS DETAILS PAGE";
    }
}

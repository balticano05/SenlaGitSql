package com.online.shop.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @GetMapping("/welcome")
    public String welcome () {
        return  "Hello World!";
    }

    @GetMapping("/api/v1/user/test")
    public String test () {
        return "test";
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('user')")
    public String userProfile () {
        return  "Info for user." ;
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasAuthority('admin')")
    public String adminProfile () {return  "Admin Info for user." ;}
}
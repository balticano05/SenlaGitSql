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
        return  "Страница приветствия" ;
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('user')")
    public String userProfile () {
        return  "Здесь отображается профиль пользователя." ;
    }
}
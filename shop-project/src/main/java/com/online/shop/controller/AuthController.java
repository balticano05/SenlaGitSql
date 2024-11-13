package com.online.shop.controller;

import com.online.shop.dto.AuthRequest;
import com.online.shop.dto.AuthResponse;
import com.online.shop.dto.RegisterRequest;
import com.online.shop.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest dto) {
        log.info("Executing registration in controller");
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest dto) {
        log.info("Executing registration for admin in controller");
        return ResponseEntity.ok(authService.registerAdmin(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest dto) {
        log.info("Executing authentication in controller");
        return ResponseEntity.ok(authService.authenticate(dto));
    }

}
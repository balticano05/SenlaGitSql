package com.online.shop.security.controller;

import com.online.shop.security.dto.AuthenticateDto;
import com.online.shop.security.dto.AuthResponse;
import com.online.shop.security.dto.RegisterDto;
import com.online.shop.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Transactional
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterDto dto) {
        log.info("Executing registration in controller");
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticateDto dto) {
        log.info("Executing authentication in controller");
        return ResponseEntity.ok(authService.authenticate(dto));
    }
}
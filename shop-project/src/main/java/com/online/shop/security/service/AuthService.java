package com.online.shop.security.service;

import com.online.shop.entity.Role;
import com.online.shop.entity.User;
import com.online.shop.repository.UserDao;
import com.online.shop.security.dto.AuthenticateDto;
import com.online.shop.security.dto.AuthResponse;
import com.online.shop.security.dto.RegisterDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterDto registerDto) {
        User user = User.builder()
                .email(registerDto.getEmail())
                .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
                .role(Role.builder().id(1L).name("user").build())
                .createdAt(LocalDateTime.now())
                .build();
        userDao.insert(user);
        String jwtToken = jwtService.generateToken(user.getEmail());
        return new AuthResponse(jwtToken);
    }

    public AuthResponse registerAdmin(RegisterDto registerDto) {
        User user = User.builder()
                .email(registerDto.getEmail())
                .password(bCryptPasswordEncoder.encode(registerDto.getPassword()))
                .role(Role.builder().id(2L).name("admin").build())
                .createdAt(LocalDateTime.now())
                .build();
        userDao.insert(user);
        String jwtToken = jwtService.generateToken(user.getEmail());
        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(AuthenticateDto authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        User user = userDao.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(user.getEmail());
        return new AuthResponse(jwtToken);
    }

}

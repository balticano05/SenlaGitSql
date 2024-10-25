package com.online.shop.controller;

import com.online.shop.dto.UserDto;
import com.online.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody UserDto userDto) {
        log.info("Executing insert method in UserController with JSON processing");
        userDto.setCreatedAt(LocalDateTime.now());
        return userService.insert(userDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        log.info("Executing update method in UserController with JSON processing");
        return userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in UserController with JSON processing");
        return userService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public List<UserDto> getAll() {
        log.info("Executing getAll method in UserController with JSON processing");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public UserDto getById(@PathVariable Long id) {
        log.info("Executing getById method in UserController with JSON processing");
        return userService.findById(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('admin')")
    public UserDto getByEmail(@PathVariable String email) {
        log.info("Executing getByEmail method in UserController with JSON processing");
        return userService.findByEmail(email);

    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasRole('admin')")
    public List<UserDto> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method.");
        return userService.findByDate(date);
    }

}
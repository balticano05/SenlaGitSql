package com.online.shop.controller;

import com.online.shop.dto.BuyCourseRequest;
import com.online.shop.dto.DateRequest;
import com.online.shop.dto.EmailRequest;
import com.online.shop.dto.UserDto;
import com.online.shop.service.TransactionService;
import com.online.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
    private final TransactionService transactionService;

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
    public List<UserDto> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Executing getAll method in UserController with JSON processing");
        return userService.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public UserDto getById(@PathVariable Long id) {
        log.info("Executing getById method in UserController with JSON processing");
        return userService.findById(id);
    }

    @PostMapping("/email")
    @PreAuthorize("hasRole('admin')")
    public UserDto getByEmail(@RequestBody EmailRequest emailRequest) {
        log.info("Executing getByEmail method in UserController with JSON processing");
        return userService.findByEmail(emailRequest.getEmail());

    }

    @PostMapping("/date")
    @PreAuthorize("hasRole('admin')")
    public List<UserDto> getByDate(@RequestBody DateRequest dateRequest) {
        log.info("Executing getByDate method.");
        return userService.findByDate(dateRequest.getBody());
    }

    @PostMapping("/course-purchase/{id}")
    @PreAuthorize("hasRole('admin') or ((hasRole('user') and @securityServiceImpl.isUserOwner(#id, authentication.name)))")
    public Long buyCourse(@PathVariable Long id, @RequestBody BuyCourseRequest request) {
        log.info("Executing buyCourse method in UserController");
        return transactionService.buyCourse(id, request);
    }

}
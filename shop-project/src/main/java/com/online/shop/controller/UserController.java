package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.UserDto;
import com.online.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody UserDto userDto) {
        log.info("Executing insert method in UserController with JSON processing");
        userDto.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.insert(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.info("Executing update method in UserController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in UserController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Executing getAll method in UserController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in UserController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        log.info("Executing getByEmail method in UserController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByEmail(email));

    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<UserDto>> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method.");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userService.findByDate(date));
    }

}
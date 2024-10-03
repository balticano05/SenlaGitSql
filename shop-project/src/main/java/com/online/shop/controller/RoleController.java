package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.RoleDto;
import com.online.shop.service.RoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    @Autowired
    public RoleController(RoleService roleService, ObjectMapper objectMapper) {
        this.roleService = roleService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Long> insert(@Valid @RequestBody RoleDto roleDto) {
        log.info("Executing insert method in RoleController with JSON processing");
        return ResponseEntity.ok(roleService.insert(roleDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        log.info("Executing update method in RoleController with JSON processing");
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in RoleController with JSON processing");
        return ResponseEntity.ok(roleService.delete(id));

    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        log.info("Executing getAll method in RoleController with JSON processing");
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in RoleController with JSON processing");
        return ResponseEntity.ok(roleService.findById(id));
    }

}
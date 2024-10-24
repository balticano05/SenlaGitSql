package com.online.shop.controller;

import com.online.shop.dto.RoleDto;
import com.online.shop.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody RoleDto roleDto) {
        log.info("Executing insert method in RoleController with JSON processing");
        return roleService.insert(roleDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public RoleDto update(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        log.info("Executing update method in RoleController with JSON processing");
        return roleService.update(id, roleDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in RoleController with JSON processing");
        return roleService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public List<RoleDto> getAll() {
        log.info("Executing getAll method in RoleController with JSON processing");
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public RoleDto getById(@PathVariable Long id) {
        log.info("Executing getById method in RoleController with JSON processing");
        return roleService.findById(id);
    }

}
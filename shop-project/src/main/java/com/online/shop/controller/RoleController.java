package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.RoleDto;
import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.online.shop.utils.StringConst.*;

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

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody RoleDto roleDto) {
        if(roleDto.getName() == null || roleDto.getDescription() == null) {
            throw new InvalidEntityDataException("Data are required");
        }
        try {
            log.info("Executing insert method in RoleController with JSON processing");
            Long id = roleService.insert(roleDto);
            String jsonResponse = objectMapper.writeValueAsString(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody RoleDto roleDto) {
        try {
            log.info("Executing update method in RoleController with JSON processing");
            RoleDto updatedRole = roleService.update(id, roleDto);
            if (updatedRole == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String jsonResponse = objectMapper.writeValueAsString(updatedRole);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            log.info("Executing delete method in RoleController with JSON processing");
            boolean isDeleted = roleService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(isDeleted);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Executing getAll method in RoleController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(roleService.getAll());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        try {
            log.info("Executing getById method in RoleController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(roleService.findById(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}
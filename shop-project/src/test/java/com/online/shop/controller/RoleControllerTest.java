package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.RoleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
class RoleControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private RoleDto getRoleDto() {
        RoleDto role = new RoleDto();
        role.setName("test");
        role.setDescription("test");
        return role;
    }

    @Test
    void insert() throws Exception {
        RoleDto roleDto = getRoleDto();
        String jsonContent = objectMapper.writeValueAsString(roleDto);
        mockMvc.perform(post("/api/roles/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        RoleDto roleDto = getRoleDto();
        String jsonContent = objectMapper.writeValueAsString(roleDto);
        mockMvc.perform(post("/api/roles/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete("/api/roles/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/roles/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/roles/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        RoleDto roleDto = new RoleDto();
        String jsonContent = objectMapper.writeValueAsString(roleDto);
        mockMvc.perform(post("/api/roles/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentRole() throws Exception {
        RoleDto roleDto = getRoleDto();
        String jsonContent = objectMapper.writeValueAsString(roleDto);
        mockMvc.perform(post("/api/roles/update/9999") // Non-existent role ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentRole() throws Exception {
        mockMvc.perform(delete("/api/roles/delete/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/roles/get/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
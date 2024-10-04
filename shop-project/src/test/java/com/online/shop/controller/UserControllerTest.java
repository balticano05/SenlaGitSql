package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.RoleDto;
import com.online.shop.dto.UserDto;
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

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private UserDto getUserDto() {
        UserDto user = new UserDto();
        user.setEmail("test@gmail.com");
        user.setPassword("482c811da5d5b4bc6d497fff5d0e0fbd");
        user.setCreatedAt(LocalDateTime.of(2024, 2, 1, 11, 0));

        RoleDto role = new RoleDto();
        role.setId(1L);
        role.setName("user");
        role.setDescription("это роль для участников курсов. Пользователи могут просматривать и взаимодействовать с контентом курсов, участвовать в обсуждениях и выполнять задания.");
        user.setRole(role);

        user.setCourses(Collections.emptyList());

        return user;
    }

    @Test
    void insert() throws Exception {
        UserDto userDto = getUserDto();
        String jsonContent = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        UserDto userDto = getUserDto();
        String jsonContent = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(get("/api/users/email/eve.davis@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByDate() throws Exception {
        mockMvc.perform(get("/api/users/date/01.02.2024")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        UserDto userDto = getUserDto();
        String jsonContent = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentUser() throws Exception {
        UserDto userDto = getUserDto();
        String jsonContent = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(put("/api/users/9999") // Non-existent user ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentUser() throws Exception {
        mockMvc.perform(delete("/api/users/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/users/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByNonExistentEmail() throws Exception {
        mockMvc.perform(get("/api/users/email/nonexistent@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/users/date/invalid-date")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
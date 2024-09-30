package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.CourseDto;
import com.online.shop.dto.TransactionDto;
import com.online.shop.dto.UserDto;
import com.online.shop.entity.Course;
import com.online.shop.entity.Transaction;
import com.online.shop.entity.User;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
class TransactionControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private TransactionDto getTransactionDto() {
        TransactionDto transaction = new TransactionDto();
        UserDto user = new UserDto();
        user.setId(1L);
        user.setEmail("admin1.shopcourses@gmail.com");
        transaction.setUser(user);
        CourseDto course = new CourseDto();
        course.setId(11L);
        transaction.setCourse(course);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setPrice(BigDecimal.valueOf(100.00));
        return transaction;
    }

    @Test
    void insert() throws Exception {
        TransactionDto transactionDto = getTransactionDto();
        String jsonContent = objectMapper.writeValueAsString(transactionDto);
        mockMvc.perform(post("/api/transactions/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        TransactionDto transactionDto = getTransactionDto();
        String jsonContent = objectMapper.writeValueAsString(transactionDto);
        mockMvc.perform(post("/api/transactions/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete("/api/transactions/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/transactions/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/transactions/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(get("/api/transactions/email/bob.johnson@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByDate() throws Exception {
        mockMvc.perform(get("/api/transactions/date/15.01.2015")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        String jsonContent = objectMapper.writeValueAsString(transactionDto);
        mockMvc.perform(post("/api/transactions/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentTransaction() throws Exception {
        TransactionDto transactionDto = getTransactionDto();
        String jsonContent = objectMapper.writeValueAsString(transactionDto);
        mockMvc.perform(post("/api/transactions/update/9999") // Non-existent transaction ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentTransaction() throws Exception {
        mockMvc.perform(delete("/api/transactions/delete/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/transactions/get/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByNonExistentEmail() throws Exception {
        mockMvc.perform(get("/api/transactions/email/nonexistent@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/transactions/date/invalid-date")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
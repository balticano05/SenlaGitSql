package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.CourseDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
class CourseControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private CourseDto getCourseDto() {
        CourseDto course = new CourseDto();
        course.setTitle("Test course");
        course.setDescription("Test course description");
        course.setCreatedAt(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
        course.setPrice(BigDecimal.valueOf(100.0));
        return course;
    }

    @Test
    void insert() throws Exception {
        CourseDto courseDto = getCourseDto();
        String jsonContent = objectMapper.writeValueAsString(courseDto);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        CourseDto courseDto = getCourseDto();
        String jsonContent = objectMapper.writeValueAsString(courseDto);
        mockMvc.perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByDate() throws Exception {
        mockMvc.perform(get("/api/courses/date/03.10.2024")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        CourseDto courseDto = new CourseDto();
        String jsonContent = objectMapper.writeValueAsString(courseDto);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentCourse() throws Exception {
        CourseDto courseDto = getCourseDto();
        String jsonContent = objectMapper.writeValueAsString(courseDto);
        mockMvc.perform(put("/api/courses/9999") // Non-existent course ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentCourse() throws Exception {
        mockMvc.perform(delete("/api/courses/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/courses/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/courses/date/invalid-date")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
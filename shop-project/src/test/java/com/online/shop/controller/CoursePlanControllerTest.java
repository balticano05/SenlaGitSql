package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.CoursePlanDto;
import com.online.shop.entity.Course;
import com.online.shop.entity.CoursePlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
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
class CoursePlanControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private CoursePlanDto getCoursePlanDto() {
        Course course = new Course();
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        course.setPrice(new BigDecimal("100.00"));
        course.setCreatedAt(LocalDateTime.now());

        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setLessonCount(10);
        coursePlan.setPracticeCount(5);
        coursePlan.setDuration(30);
        coursePlan.setCourse(course);
        return modelMapper.map(coursePlan, CoursePlanDto.class);
    }

    @Test
    void insert() throws Exception {
        CoursePlanDto coursePlanDto = getCoursePlanDto();
        String jsonContent = objectMapper.writeValueAsString(coursePlanDto);

        mockMvc.perform(post("/api/course-plans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        CoursePlanDto coursePlanDto = getCoursePlanDto();
        String jsonContent = objectMapper.writeValueAsString(coursePlanDto);

        mockMvc.perform(put("/api/course-plans/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        mockMvc.perform(delete("/api/course-plans/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/course-plans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/course-plans/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        CoursePlanDto coursePlanDto = new CoursePlanDto(); // Assuming this is invalid
        String jsonContent = objectMapper.writeValueAsString(coursePlanDto);

        mockMvc.perform(post("/api/course-plans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentCoursePlan() throws Exception {
        CoursePlanDto coursePlanDto = getCoursePlanDto();
        String jsonContent = objectMapper.writeValueAsString(coursePlanDto);

        mockMvc.perform(put("/api/course-plans/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentCoursePlan() throws Exception {
        mockMvc.perform(delete("/api/course-plans/9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/course-plans/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
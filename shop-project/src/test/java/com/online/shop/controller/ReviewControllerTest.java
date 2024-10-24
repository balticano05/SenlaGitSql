package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.config.AppConfig;
import com.online.shop.dto.CourseDto;
import com.online.shop.dto.ReviewDto;
import com.online.shop.dto.UserDto;
import com.online.shop.security.dto.AuthRequest;
import com.online.shop.security.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@WebAppConfiguration
@Transactional
class ReviewControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${secret}") // Убедитесь, что вы указали правильный путь к вашему secret
    private String jwtSecret;

    private String generateJwtToken(String email) {
        // Создаем токен JWT для тестируемого пользователя
        // Используем JwtService, если он доступен, или можно реализовать это с помощью Jwts.builder()
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    private ReviewDto getReviewDto() {
        ReviewDto review = new ReviewDto();
        review.setContent("Тестовый контент отзыва");
        review.setCreatedAt(LocalDateTime.now());
        review.setRating(4);
        review.setCourse(CourseDto.builder().id(4L).build());
        review.setUser(UserDto.builder().id(4L).email("bob.johnson@gmail.com").build()); // Указываем email тестируемого пользователя
        return review;
    }

    @Test
    void insert() throws Exception {
        ReviewDto reviewDto = getReviewDto();
        String jsonContent = objectMapper.writeValueAsString(reviewDto);
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(post("/api/v1/reviews")
                        .header("Authorization", "Bearer " + jwtToken) // Добавляем токен в заголовок
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        ReviewDto reviewDto = getReviewDto();
        String jsonContent = objectMapper.writeValueAsString(reviewDto);
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(put("/api/v1/reviews/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEntity() throws Exception {
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(delete("/api/v1/reviews/1")
                .header("Authorization","Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/front")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getReviewsByUser() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/front/email/bob.johnson@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByDate() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/front/date/16.01.2015")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertWithInvalidData() throws Exception {
        ReviewDto reviewDto = new ReviewDto(); // Пустой DTO
        String jsonContent = objectMapper.writeValueAsString(reviewDto);
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(post("/api/v1/reviews")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentReview() throws Exception {
        ReviewDto reviewDto = getReviewDto();
        String jsonContent = objectMapper.writeValueAsString(reviewDto);
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(put("/api/v1/reviews/9999") // Не существующий ID отзыва
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNonExistentReview() throws Exception {
        String jwtToken = generateJwtToken("bob.johnson@gmail.com");

        mockMvc.perform(delete("/api/v1/reviews/9999") // Не существующий ID отзыва
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidId() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/invalid-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByNonExistentEmail() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/front/email/nonexistent@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/front/date/invalid-date")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
}

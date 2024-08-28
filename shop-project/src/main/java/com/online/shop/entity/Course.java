package com.online.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Course {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private CoursePlan coursePlan;
    private List<Review> reviews;
    private List<Category> categories;
}
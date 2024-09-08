package com.online.shop.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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
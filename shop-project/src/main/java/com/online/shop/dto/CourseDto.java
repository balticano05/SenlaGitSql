package com.online.shop.dto;

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
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private CoursePlanDto coursePlan;
    private List<ReviewDto> reviews;
    private List<CategoryDto> categories;
}
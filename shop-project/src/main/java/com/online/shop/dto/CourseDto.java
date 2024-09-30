package com.online.shop.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    private CoursePlanDto coursePlan;
    private LocalDateTime createdAt;
    private List<CategoryDto> categories;
}
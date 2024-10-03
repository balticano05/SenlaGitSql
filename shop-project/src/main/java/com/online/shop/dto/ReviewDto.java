package com.online.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ReviewDto {
    private Long id;
    @NotNull(message = "User cannot be null")
    private UserDto user;
    @NotNull(message = "Course cannot be null")
    private CourseDto course;
    @NotNull(message = "Content cannot be null")
    private String content;
    @NotNull(message = "Rating cannot be null")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 10, message = "Rating must be at most 10")
    private Integer rating;
    private LocalDateTime createdAt;
}
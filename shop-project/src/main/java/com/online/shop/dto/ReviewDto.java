package com.online.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ReviewDto {
    private Long id;
    private UserDto user;
    private CourseDto course;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}
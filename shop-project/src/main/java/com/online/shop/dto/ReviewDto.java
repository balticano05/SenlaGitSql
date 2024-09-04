package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDto {
    private Long id;
    private UserDto userDto;
    private CourseDto courseDto;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}
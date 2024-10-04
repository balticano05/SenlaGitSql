package com.online.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CoursePlanDto {
    private Long id;
    @NotNull(message = "Count of lessons cannot be null")
    @Min(value = 1, message = "Count of lessons must be at least 0")
    @Max(value = 100000, message = "Count of lessons must be at most 100000")
    private Integer lessonCount;
    @NotNull(message = "Count of practices cannot be null")
    @Min(value = 1, message = "Count of practices must be at least 0")
    @Max(value = 100000, message = "Count of practices must be at most 100000")
    private Integer practiceCount;
    @NotNull(message = "Duration cannot be nul")
    @Min(value = 1, message = "Duration must be at least 1")
    @Max(value = 100000, message = "Duration must be at most 100000")
    private Integer duration;
    @NotNull(message = "Course cannot be null")
    private CourseDto course;
}
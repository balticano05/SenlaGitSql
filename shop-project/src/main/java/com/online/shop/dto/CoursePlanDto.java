package com.online.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CoursePlanDto {
    private Long id;
    private Integer lessonCount;
    private Integer practiceCount;
    private Integer duration;
    private CourseDto course;
}
package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoursePlanDto {
    private Long id;
    private Integer lessonCount;
    private Integer practiceCount;
    private Integer duration;
}

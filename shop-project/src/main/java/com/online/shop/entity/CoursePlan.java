package com.online.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CoursePlan {
    private Long id;
    private Integer lessonCount;
    private Integer practiceCount;
    private Integer duration;
}

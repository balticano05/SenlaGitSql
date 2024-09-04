package com.online.shop.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CoursePlan {
    private Long id;
    private Integer lessonCount;
    private Integer practiceCount;
    private Integer duration;
}
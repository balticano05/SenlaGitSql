package com.online.shop.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Review {
    private Long id;
    private User user;
    private Course course;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}
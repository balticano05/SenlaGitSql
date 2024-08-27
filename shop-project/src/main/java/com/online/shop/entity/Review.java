package com.online.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {
    private Long id;
    private User user;
    private Course course;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}

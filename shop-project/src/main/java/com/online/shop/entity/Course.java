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
public class Course {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private LocalDateTime createdAt;
}

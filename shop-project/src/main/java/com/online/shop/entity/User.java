package com.online.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private List<Course> courses;
    private List<Review> reviews;
}
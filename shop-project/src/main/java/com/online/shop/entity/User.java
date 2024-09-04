package com.online.shop.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private List<Course> courses = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
}
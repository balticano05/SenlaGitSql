package com.online.shop.dto;

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
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private RoleDto role;
    private LocalDateTime createdAt;
    private List<CourseDto> courses;
    private List<ReviewDto> reviews;
    private List<TransactionDto> transactions;
}
package com.online.shop.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private RoleDto role;
    private LocalDateTime createdAt;
    private List<CourseDto> courses;
}
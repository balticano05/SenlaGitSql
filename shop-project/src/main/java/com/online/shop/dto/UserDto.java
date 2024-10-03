package com.online.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 4, max = 254, message = "Email must be between 4 and 50 characters")
    private String email;
    @Size(min = 1, max = 128, message = "Password must be between 1 and 128 characters")
    private String password;
    @NotNull(message = "Role cannot be null")
    private RoleDto role;
    private LocalDateTime createdAt;
    private List<CourseDto> courses;
}
package com.online.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Email cannot be null")
    @Size(min = 11, max = 254, message = "Email must be between 11 and 254 characters")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a valid gmail address")
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 1, max = 128, message = "Password must be between 1 and 128 characters")
    private String password;
    @NotNull(message = "Role cannot be null")
    private RoleDto role;
    private LocalDateTime createdAt;
    private List<CourseDto> courses;
}
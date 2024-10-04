package com.online.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RoleDto {
    private Long id;
    @NotNull
    @Size(min = 1, max = 40, message = "Role must be between 1 and 40 characters")
    private String name;
    @NotNull(message = "Description cannot be null")
    private String description;
}
package com.online.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RoleDto {
    private Long id;
    private String name;
    private String description;
}
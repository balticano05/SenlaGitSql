package com.online.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CourseDto {
    private Long id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 500, message = "Title must be between 1 and 500 characters")
    private String title;
    @NotNull(message = "Description cannot be null")
    private String description;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be at least 0")
    @Max(value = 100000000, message = "Price must be at most 100000000")
    private BigDecimal price;
    private LocalDateTime createdAt;
    private List<CategoryDto> categories;
}
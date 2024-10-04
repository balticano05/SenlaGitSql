package com.online.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TransactionDto {
    private Long id;
    @NotNull(message = "User cannot be null")
    private UserDto user;
    @NotNull(message = "Course cannot be null")
    private CourseDto course;
    private LocalDateTime dateTime;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be at least 0")
    @Max(value = 100000000, message = "Price must be at most 100000000")
    private BigDecimal price;
}
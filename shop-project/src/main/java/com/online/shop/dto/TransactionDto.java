package com.online.shop.dto;

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
    private UserDto user;
    private CourseDto course;
    private LocalDateTime dateTime;
    private BigDecimal price;
}
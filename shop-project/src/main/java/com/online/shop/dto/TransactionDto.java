package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {
    private Long id;
    private UserDto user;
    private CourseDto course;
    private LocalDateTime dateTime;
    private BigDecimal price;
}
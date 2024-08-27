package com.online.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionsDto {
    private Long id;
    private UserDto user;
    private CourseDto course;
    private LocalDateTime dateTime;
    private Float price;
}

package com.online.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
    private Long id;
    private User user;
    private Course course;
    private LocalDateTime dateTime;
    private Float price;
}

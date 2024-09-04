package com.online.shop.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {
    private Long id;
    private User user;
    private Course course;
    private LocalDateTime dateTime;
    private BigDecimal price;
}
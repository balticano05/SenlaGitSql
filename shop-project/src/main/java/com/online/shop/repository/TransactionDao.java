package com.online.shop.repository;

import com.online.shop.entity.Transaction;

import java.util.Optional;

public interface TransactionDao {
    Optional<Transaction> findById(Long id);
}
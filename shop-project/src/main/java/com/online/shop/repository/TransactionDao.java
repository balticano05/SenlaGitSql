package com.online.shop.repository;

import com.online.shop.dto.CourseDto;
import com.online.shop.entity.Transaction;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    Optional<Transaction> getById(Long id);

    List<Transaction> getAll(PageRequest pageRequest);

    Long insert(Transaction entity);

    Optional<Transaction> update(Long id, Transaction entity);

    Boolean delete(Long id);

    List<Transaction> findTransactionsByEmail(String email);

    List<Transaction> findByCreateDate(String createdAt);

    List<Transaction> findTransactionsById(Long id);
}
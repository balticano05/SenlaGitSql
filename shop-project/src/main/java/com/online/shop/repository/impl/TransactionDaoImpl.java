package com.online.shop.repository.impl;

import com.online.shop.entity.Transaction;
import com.online.shop.repository.TransactionDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private List<Transaction> transactions;

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.empty();
    }

}
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
    public Optional<Transaction> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @Override
    public Long insert(Transaction entity) {
        return 0L;
    }

    @Override
    public Optional<Transaction> update(Long id, Transaction entity) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long entity) {
        return null;
    }

}
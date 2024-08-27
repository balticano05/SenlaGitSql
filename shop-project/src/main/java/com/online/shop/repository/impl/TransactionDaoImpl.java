package com.online.shop.repository.impl;

import com.online.shop.entity.Transaction;
import com.online.shop.repository.GeneralDao;

import java.util.List;

public class TransactionDaoImpl implements GeneralDao<Transaction> {

    private List<Transaction> transactions;

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @Override
    public void add(Transaction entity) {

    }

    @Override
    public void update(Long id, Transaction entity) {

    }

    @Override
    public void delete(Long entity) {

    }
}

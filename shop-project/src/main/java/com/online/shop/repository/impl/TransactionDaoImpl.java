package com.online.shop.repository.impl;

import com.online.shop.entity.Transaction;
import com.online.shop.repository.CrudOperationsDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements CrudOperationsDao<Transaction> {

    private List<Transaction> transactions;

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @Override
    public void insert(Transaction entity) {

    }

    @Override
    public void update(Long id, Transaction entity) {

    }

    @Override
    public void delete(Long entity) {

    }

}

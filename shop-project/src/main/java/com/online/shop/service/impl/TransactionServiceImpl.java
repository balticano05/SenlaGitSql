package com.online.shop.service.impl;

import com.online.shop.entity.Transaction;
import com.online.shop.service.CrudService;
import com.online.shop.service.TransactionService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class TransactionServiceImpl extends CrudService<Transaction> implements TransactionService {

    public TransactionServiceImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Transaction add(Transaction entityDto) {
        return null;
    }

    @Override
    public Transaction update(Long id, Transaction entityDto) {
        return null;
    }

    @Override
    public Transaction getById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @Override
    public Transaction delete(Long id) {
        return null;
    }
}

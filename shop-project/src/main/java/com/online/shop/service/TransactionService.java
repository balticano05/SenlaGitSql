package com.online.shop.service;

import com.online.shop.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto  insert(TransactionDto entityDto);

    TransactionDto  update(Long id, TransactionDto  entityDto);

    TransactionDto  findById(Long id);

    List<TransactionDto > getAll();

    Boolean delete(Long id);
}
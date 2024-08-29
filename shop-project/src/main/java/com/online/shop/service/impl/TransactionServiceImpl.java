package com.online.shop.service.impl;

import com.online.shop.dto.TransactionDto;
import com.online.shop.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public TransactionDto insert(TransactionDto entityDto) {
        return null;
    }

    @Override
    public TransactionDto update(Long id, TransactionDto entityDto) {
        return null;
    }

    @Override
    public TransactionDto findById(Long id) {
        return null;
    }

    @Override
    public List<TransactionDto> getAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

}

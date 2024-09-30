package com.online.shop.service.impl;

import com.online.shop.repository.TransactionDao;
import com.online.shop.utils.Validator;
import com.online.shop.service.TransactionService;
import com.online.shop.dto.TransactionDto;
import com.online.shop.entity.Transaction;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;
    private ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao, ModelMapper modelMapper) {
        this.transactionDao = transactionDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(TransactionDto entityDto) {
        if (entityDto == null) {
            log.error("TransactionDto is null in insert method");
            throw new IllegalArgumentException("TransactionDto cannot be null");
        }
        log.info("Executing insert method in TransactionServiceImpl with DTO: {}", entityDto);
        return transactionDao.insert(modelMapper.map(entityDto, Transaction.class));
    }

    @Override
    public TransactionDto update(Long id, TransactionDto entityDto) {
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("TransactionDto is null in update method");
            throw new IllegalArgumentException("TransactionDto cannot be null");
        }
        log.info("Executing update method in TransactionServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(transactionDao.update(id, modelMapper.map(entityDto, Transaction.class)), TransactionDto.class);
    }

    @Override
    public TransactionDto findById(Long id) {
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing findById method in TransactionServiceImpl for ID: {}", id);
        return modelMapper.map(transactionDao.getById(id), TransactionDto.class);
    }

    @Override
    public List<TransactionDto> getAll() {
        log.info("Executing getAll method in TransactionServiceImpl");
        return transactionDao.getAll().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        log.info("Executing delete method in TransactionServiceImpl for ID: {}", id);
        return transactionDao.delete(id);
    }

    @Override
    public List<TransactionDto> getTransactionsByEmail(String email) {
        if (email == null) {
            log.error("Email is null in getTransactionsByEmail method");
            throw new IllegalArgumentException("Email cannot be null");
        }
        log.info("Executing getTransactionsByEmail method in TransactionServiceImpl for email: {}", email);
        return transactionDao.findTransactionsByEmail(email).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByDate(String date) {
        if (date == null) {
            log.error("Date is null in findByDate method");
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!Validator.isValidDateFormat(date)) {
            log.error("Invalid date format in findByDate method");
        }
        log.info("Executing findByDate method in TransactionServiceImpl for date: {}", date);
        return transactionDao.findByCreateDate(date).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

}

package com.online.shop.service.impl;

import com.online.shop.repository.TransactionDao;
import com.online.shop.utils.Validator;
import com.online.shop.service.TransactionService;
import com.online.shop.dto.TransactionDto;
import com.online.shop.entity.Transaction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final ModelMapper modelMapper;

    @Override
    public Long insert(TransactionDto entityDto) {
        log.info("Executing insert method in TransactionServiceImpl with DTO: {}", entityDto);
        if (entityDto == null) {
            log.error("TransactionDto is null in insert method");
            throw new IllegalArgumentException("TransactionDto cannot be null");
        }
        return transactionDao.insert(modelMapper.map(entityDto, Transaction.class));
    }

    @Override
    public TransactionDto update(Long id, TransactionDto entityDto) {
        log.info("Executing update method in TransactionServiceImpl for ID: {} with DTO: {}", id, entityDto);
        if (id == null) {
            log.error("ID is null in update method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (entityDto == null) {
            log.error("TransactionDto is null in update method");
            throw new IllegalArgumentException("TransactionDto cannot be null");
        }
        Optional<Transaction> updatedTransaction = Optional.ofNullable(transactionDao.update(id, modelMapper.map(entityDto, Transaction.class))
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found")));
        return modelMapper.map(updatedTransaction, TransactionDto.class);
    }

    @Override
    public TransactionDto findById(Long id) {
        log.info("Executing findById method in TransactionServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in findById method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Transaction> foundTransaction = Optional.ofNullable(transactionDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found")));
        return modelMapper.map(foundTransaction, TransactionDto.class);
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
        log.info("Executing delete method in TransactionServiceImpl for ID: {}", id);
        if (id == null) {
            log.error("ID is null in delete method");
            throw new IllegalArgumentException("ID cannot be null");
        }
        return transactionDao.delete(id);
    }

    @Override
    public List<TransactionDto> getTransactionsByEmail(String email) {
        log.info("Executing getTransactionsByEmail method in TransactionServiceImpl for email: {}", email);
        if (email == null) {
            log.error("Email is null in getTransactionsByEmail method");
            throw new IllegalArgumentException("Email cannot be null");
        }
        List<Transaction> foundTransactions = transactionDao.findTransactionsByEmail(email);
        return Optional.ofNullable(foundTransactions)
                .filter(transactions -> !transactions.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Transactions with email " + email + " not found");
                })
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByDate(String date) {
        log.info("Executing findByDate method in TransactionServiceImpl for date: {}", date);
        if (!Validator.isValidDateFormat(date) || date == null) {
            log.error("Invalid date format in findByDate method");
            throw new IllegalArgumentException("Invalid date format in findByDate method");
        }
        List<Transaction> foundTransactions = transactionDao.findByCreateDate(date);
        return Optional.ofNullable(foundTransactions)
                .filter(transactions -> !transactions.isEmpty())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("List of transactions is empty in findByDate method");
                })
                .stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

}
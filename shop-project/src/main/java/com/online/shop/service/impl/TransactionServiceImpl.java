package com.online.shop.service.impl;

import com.online.shop.repository.TransactionDao;
import com.online.shop.service.TransactionService;
import com.online.shop.dto.TransactionDto;
import com.online.shop.entity.Transaction;
import com.online.shop.repository.impl.TransactionDaoImpl;
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
        log.info("Executing insert method in TransactionServiceImpl with DTO: {}", entityDto);
        return transactionDao.insert(modelMapper.map(entityDto, Transaction.class));
    }

    @Override
    public TransactionDto update(Long id, TransactionDto entityDto) {
        log.info("Executing update method in TransactionServiceImpl for ID: {} with DTO: {}", id, entityDto);
        return modelMapper.map(transactionDao.update(id, modelMapper.map(entityDto, Transaction.class)), TransactionDto.class);
    }

    @Override
    public TransactionDto findById(Long id) {
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
        log.info("Executing delete method in TransactionServiceImpl for ID: {}", id);
        return transactionDao.delete(id);
    }

    @Override
    public List<TransactionDto> getTransactionsByEmail(String email) {
        log.info("Executing getTransactionsByEmail method in TransactionServiceImpl for email: {}", email);
        return transactionDao.findTransactionsByEmail(email).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByDate(String date) {
        log.info("Executing findByDate method in TransactionServiceImpl for date: {}", date);
        return transactionDao.findByCreationDate(date).stream()
                .map(transaction -> modelMapper.map(transactionDao, TransactionDto.class))
                .collect(Collectors.toList());
    }

}

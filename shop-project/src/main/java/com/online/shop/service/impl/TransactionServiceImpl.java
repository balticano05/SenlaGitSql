package com.online.shop.service.impl;

import com.online.shop.service.TransactionService;
import com.online.shop.dto.TransactionDto;
import com.online.shop.entity.Transaction;
import com.online.shop.repository.impl.TransactionDaoImpl;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.online.shop.Application.log;
import static com.online.shop.utils.StringConst.*;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private TransactionDaoImpl transactionDao;
    private ModelMapper modelMapper;

    @Autowired
    public TransactionServiceImpl(TransactionDaoImpl transactionDao, ModelMapper modelMapper) {
        this.transactionDao = transactionDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long insert(TransactionDto entityDto) {
        log.info(LOG_EXECUTING_INSERT_METHOD);
        return transactionDao.insert(modelMapper.map(entityDto, Transaction.class));
    }

    @Override
    public TransactionDto update(Long id, TransactionDto entityDto) {
        log.info(LOG_EXECUTING_UPDATE_METHOD);
        return modelMapper.map(transactionDao.update(id, modelMapper.map(entityDto, Transaction.class)), TransactionDto.class);
    }

    @Override
    public TransactionDto findById(Long id) {
        log.info(LOG_EXECUTING_FIND_BY_ID_METHOD);
        return modelMapper.map(transactionDao.getById(id), TransactionDto.class);
    }

    @Override
    public List<TransactionDto> getAll() {
        log.info(LOG_EXECUTING_GET_ALL_METHOD);
        return transactionDao.getAll().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        log.info(LOG_EXECUTING_DELETE_METHOD);
        return transactionDao.delete(id);
    }

    @Override
    public List<TransactionDto> getTransactionsByEmail(String email) {
        log.info(LOG_EXECUTING_GET_TRANSACTIONS_METHOD);
        return transactionDao.findTransactionsByEmail(email).stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByDate(String date) {
        log.info(LOG_EXECUTING_GET_BY_DATE_METHOD);
        return transactionDao.findByCreatedAt(date).stream()
                .map(transaction -> modelMapper.map(transactionDao, TransactionDto.class))
                .collect(Collectors.toList());
    }

}

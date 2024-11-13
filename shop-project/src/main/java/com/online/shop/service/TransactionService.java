package com.online.shop.service;

import com.online.shop.dto.BuyCourseRequest;
import com.online.shop.dto.TransactionDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TransactionService {
    Long insert(TransactionDto entityDto);

    TransactionDto update(Long id, TransactionDto entityDto);

    TransactionDto findById(Long id);

    List<TransactionDto> getAll(PageRequest pageRequest);

    Boolean delete(Long id);

    public List<TransactionDto> getTransactionsByEmail(String email);

    List<TransactionDto> findByDate(String date);

    Long buyCourse(Long id, BuyCourseRequest request);
}
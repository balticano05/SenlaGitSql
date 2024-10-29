package com.online.shop.service.impl;

import com.online.shop.dto.TransactionDto;
import com.online.shop.entity.Transaction;
import com.online.shop.repository.TransactionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionDao transactionDao;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction transaction;
    private TransactionDto transactionDto;

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setPrice(BigDecimal.valueOf(100.0));
        transaction.setDateTime(LocalDateTime.of(2023, 10, 5, 14, 30, 0));

        transactionDto = new TransactionDto();
        transactionDto.setId(1L);
        transactionDto.setDateTime(LocalDateTime.now());
        transactionDto.setPrice(BigDecimal.valueOf(100.0));
        transactionDto.setDateTime(LocalDateTime.of(2023, 10, 5, 14, 30, 0));
    }

    @Test
    void insert() {
        when(modelMapper.map(any(TransactionDto.class), eq(Transaction.class))).thenReturn(transaction);
        when(transactionDao.insert(any(Transaction.class))).thenReturn(1L);
        Long transactionId = transactionService.insert(transactionDto);
        assertEquals(1L, transactionId);
        verify(transactionDao, times(1)).insert(any(Transaction.class));
    }

    @Test
    void update() {
        when(modelMapper.map(any(TransactionDto.class), eq(Transaction.class))).thenReturn(transaction);
        when(transactionDao.update(anyLong(), any(Transaction.class))).thenReturn(Optional.of(transaction));
        when(modelMapper.map(any(Optional.class), eq(TransactionDto.class))).thenReturn(transactionDto);
        TransactionDto updatedTransactionDto = transactionService.update(1L, transactionDto);
        assertEquals(transactionDto.getPrice(), updatedTransactionDto.getPrice());
        verify(transactionDao, times(1)).update(anyLong(), any(Transaction.class));
    }

    @Test
    void findById() {
        when(transactionDao.getById(anyLong())).thenReturn(Optional.of(transaction));
        when(modelMapper.map(any(Optional.class), eq(TransactionDto.class))).thenReturn(transactionDto);
        TransactionDto foundTransactionDto = transactionService.findById(1L);
        assertEquals(transactionDto.getPrice(), foundTransactionDto.getPrice());
        verify(transactionDao, times(1)).getById(anyLong());
    }

    @Test
    void getAll() {
        when(transactionDao.getAll(PageRequest.of(0,10))).thenReturn(Collections.singletonList(transaction));
        when(modelMapper.map(any(Transaction.class), eq(TransactionDto.class))).thenReturn(transactionDto);
        List<TransactionDto> transactions = transactionService.getAll(PageRequest.of(0,10));
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        verify(transactionDao, times(1)).getAll(PageRequest.of(0,10));
    }

    @Test
    void delete() {
        when(transactionDao.delete(anyLong())).thenReturn(true);
        Boolean result = transactionService.delete(1L);
        assertTrue(result);
        verify(transactionDao, times(1)).delete(anyLong());
    }

    @Test
    void getTransactionsByEmail() {
        when(transactionDao.findTransactionsByEmail(anyString())).thenReturn(Collections.singletonList(transaction));
        when(modelMapper.map(any(Transaction.class), eq(TransactionDto.class))).thenReturn(transactionDto);
        List<TransactionDto> transactions = transactionService.getTransactionsByEmail("test@mail.com");
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        verify(transactionDao, times(1)).findTransactionsByEmail(anyString());
    }

    @Test
    void findByDate() {
        when(transactionDao.findByCreateDate(anyString())).thenReturn(Collections.singletonList(transaction));
        when(modelMapper.map(any(Transaction.class), eq(TransactionDto.class))).thenReturn(transactionDto);
        List<TransactionDto> transactions = transactionService.findByDate("05.10.2023");
        assertFalse(transactions.isEmpty());
        assertEquals(1, transactions.size());
        verify(transactionDao, times(1)).findByCreateDate(anyString());
    }

    @Test
    void insertNegative() {
        when(modelMapper.map(any(TransactionDto.class), eq(Transaction.class))).thenReturn(transaction);
        when(transactionDao.insert(any(Transaction.class))).thenThrow(new RuntimeException("Insert failed"));
        assertThrows(RuntimeException.class, () -> transactionService.insert(transactionDto));
        verify(transactionDao, times(1)).insert(any(Transaction.class));
    }

    @Test
    void updateNegative() {
        assertThrows(IllegalArgumentException.class, () -> transactionService.update(null, transactionDto));
        verify(transactionDao, never()).update(anyLong(), any(Transaction.class));
    }

    @Test
    void findByIdNegative() {
        assertThrows(IllegalArgumentException.class, () -> transactionService.findById(null));
        verify(transactionDao, never()).getById(anyLong());
    }

    @Test
    void getAllNegative() {
        when(transactionDao.getAll(PageRequest.of(0,10))).thenReturn(Collections.emptyList());
        List<TransactionDto> transactions = transactionService.getAll(PageRequest.of(0,10));
        assertTrue(transactions.isEmpty());
        verify(transactionDao, times(1)).getAll(PageRequest.of(0,10));
    }

    @Test
    void deleteNegative() {
        when(transactionDao.delete(anyLong())).thenReturn(false);
        Boolean result = transactionService.delete(1L);
        assertFalse(result);
        verify(transactionDao, times(1)).delete(anyLong());
    }

    @Test
    void getTransactionsByEmailNegative() {
        assertThrows(IllegalArgumentException.class, () -> transactionService.getTransactionsByEmail(null));
        verify(transactionDao, never()).findTransactionsByEmail(anyString());
    }

    @Test
    void findByDateNegative() {
        when(transactionDao.findByCreateDate(anyString())).thenReturn(Collections.emptyList());
        List<TransactionDto> transactions = transactionService.findByDate("05.10.2023");
        assertTrue(transactions.isEmpty());
        verify(transactionDao, times(1)).findByCreateDate(anyString());
    }

}
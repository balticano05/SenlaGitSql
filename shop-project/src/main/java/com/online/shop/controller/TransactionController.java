package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.TransactionDto;
import com.online.shop.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    public String insert(String jsonEntity) {
        try {
            log.info("Executing insert method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.insert(objectMapper.readValue(jsonEntity, TransactionDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String update(Long id, String jsonEntity) {
        try {
            log.info("Executing update method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.update(id, objectMapper.readValue(jsonEntity, TransactionDto.class)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String delete(Long id) {
        try {
            log.info("Executing delete method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.delete(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getAll() {
        try {
            log.info("Executing getAll method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.getAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getById(Long id) {
        try {
            log.info("Executing getById method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.findById(id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getTransactionsByUser(String email) {
        try {
            log.info("Executing getTransactionsByUser method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.getTransactionsByEmail(email));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    public String getByDate(String date) {
        try {
            log.info("Executing getByDate method in TransactionController with JSON processing");
            return objectMapper.writeValueAsString(transactionService.findByDate(date));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}
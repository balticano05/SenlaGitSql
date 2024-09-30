package com.online.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.TransactionDto;
import com.online.shop.exceptions.InvalidEntityDataException;
import com.online.shop.service.TransactionService;
import com.online.shop.utils.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.online.shop.utils.StringConst.*;

@Slf4j
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody TransactionDto transactionDto) {
        if(transactionDto.getUser() == null || transactionDto.getCourse() == null
                || transactionDto.getPrice() == null) {
            throw new InvalidEntityDataException("Data are required");
        }
        try {
            log.info("Executing insert method in TransactionController with JSON processing");
            Long id = transactionService.insert(transactionDto);
            String jsonResponse = objectMapper.writeValueAsString(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody TransactionDto transactionDto) {
        try {
            log.info("Executing update method in TransactionController with JSON processing");
            TransactionDto updatedTransaction = transactionService.update(id, transactionDto);
            if (updatedTransaction == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            String jsonResponse = objectMapper.writeValueAsString(updatedTransaction);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            log.info("Executing delete method in TransactionController with JSON processing");
            boolean isDeleted = transactionService.delete(id);
            if (!isDeleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(isDeleted);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        try {
            log.info("Executing getAll method in TransactionController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(transactionService.getAll());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        try {
            log.info("Executing getById method in TransactionController with JSON processing");
            String jsonResponse = objectMapper.writeValueAsString(transactionService.findById(id));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> getTransactionsByUser(@PathVariable String email) {
        try {
            log.info("Executing getTransactionsByUser method in TransactionController with JSON processing");
            List<TransactionDto> transactions = transactionService.getTransactionsByEmail(email);
            System.out.println(transactions.size());
            if (transactions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transactions not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(transactions);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<String> getByDate(@PathVariable String date) {
        try {
            log.info("Executing getByDate method in TransactionController with JSON processing");
            if(!Validator.isValidDateFormat(date)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad format of date.");
            }
            List<TransactionDto> transactions = transactionService.findByDate(date);
            if (transactions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transactions not found");
            }
            String jsonResponse = objectMapper.writeValueAsString(transactions);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(EXCEPTION_PROCESSING_JSON, e);
        }
    }

}
package com.online.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.shop.dto.TransactionDto;
import com.online.shop.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody TransactionDto transactionDto) {
        log.info("Executing insert method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.insert(transactionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> update(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        log.info("Executing update method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.update(id, transactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("Executing delete method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll() {
        log.info("Executing getAll method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id) {
        log.info("Executing getById method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByUser(@PathVariable String email) {
        log.info("Executing getTransactionsByUser method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.getTransactionsByEmail(email));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TransactionDto>> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in TransactionController with JSON processing");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(transactionService.findByDate(date));
    }

}
package com.online.shop.controller;

import com.online.shop.dto.TransactionDto;
import com.online.shop.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Long insert(@Valid @RequestBody TransactionDto transactionDto) {
        log.info("Executing insert method in TransactionController with JSON processing");
        return transactionService.insert(transactionDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public TransactionDto update(@PathVariable Long id, @Valid @RequestBody TransactionDto transactionDto) {
        log.info("Executing update method in TransactionController with JSON processing");
        return transactionService.update(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Boolean delete(@PathVariable Long id) {
        log.info("Executing delete method in TransactionController with JSON processing");
        return transactionService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public List<TransactionDto> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        log.info("Executing getAll method in TransactionController with JSON processing");
        return transactionService.getAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public TransactionDto getById(@PathVariable Long id) {
        log.info("Executing getById method in TransactionController with JSON processing");
        return transactionService.findById(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('user')")
    public List<TransactionDto> getTransactionsByUser(@PathVariable String email) {
        log.info("Executing getTransactionsByUser method in TransactionController with JSON processing");
        return transactionService.getTransactionsByEmail(email);
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasRole('admin')")
    public List<TransactionDto> getByDate(@PathVariable String date) {
        log.info("Executing getByDate method in TransactionController with JSON processing");
        return transactionService.findByDate(date);
    }

}
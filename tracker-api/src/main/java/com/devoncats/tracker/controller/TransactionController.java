package com.devoncats.tracker.controller;

import com.devoncats.tracker.mapper.interfaces.Mapper;
import com.devoncats.tracker.domain.dto.TransactionDto;
import com.devoncats.tracker.domain.entity.TransactionEntity;
import com.devoncats.tracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    private final Mapper<TransactionEntity, TransactionDto> transactionMapper;

    public TransactionController(TransactionService transactionService, Mapper<TransactionEntity, TransactionDto> transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDto);
        TransactionEntity createdTransaction = transactionService.createTransaction(transactionEntity);

        return new ResponseEntity<>(transactionMapper.toDto(createdTransaction), HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions(@RequestParam(value = "month", required = false) Integer month) {
        List<TransactionEntity> transactions = month != null
                ? transactionService.getTransactionByDateMonthAfter(month)
                : transactionService.getAllTransactions();

        return new ResponseEntity<>(
                transactions.stream()
                        .map(transactionMapper::toDto)
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") Long id) {
        Optional<TransactionEntity> transaction = transactionService.getTransactionById(id);

        return transaction.map(transactionEntity -> {
            TransactionDto transactionDto = transactionMapper.toDto(transactionEntity);

            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") Long id, @RequestBody TransactionDto transactionDto) {
        if (!transactionService.isTransaction(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionDto);
        TransactionEntity updatedTransaction = transactionService.updateTransaction(transactionEntity, id);

        return new ResponseEntity<>(transactionMapper.toDto(updatedTransaction), HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable("id") Long id) {
        if (!transactionService.isTransaction(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        transactionService.deleteTransaction(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

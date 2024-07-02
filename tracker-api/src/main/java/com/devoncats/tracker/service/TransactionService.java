package com.devoncats.tracker.service;

import com.devoncats.tracker.exception.EntityNotFoundException;
import com.devoncats.tracker.domain.entity.TransactionEntity;
import com.devoncats.tracker.repository.TransactionRepository;
import com.devoncats.tracker.service.interfaces.Services;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionService implements Services {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        transactionEntity.setCreatedDate(ZonedDateTime.now());
        transactionEntity.setUpdatedDate(ZonedDateTime.now());

        return transactionRepository.save(transactionEntity);
    }

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return StreamSupport.stream(transactionRepository
                .findAllSortedByDate()
                .spliterator(),
                false
            ).collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionEntity> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<TransactionEntity> getTransactionByDateMonthAfter(Integer month) {
        return new ArrayList<>(transactionRepository
                .findTransactionEntitiesByDateMonthAfter(month));
    }

    @Override
    public TransactionEntity updateTransaction(TransactionEntity transactionEntity, Long id) {
        transactionEntity.setId(id);

        return transactionRepository.findById(id).map(transaction -> {
            Optional.ofNullable(transactionEntity.getAccount()).ifPresent(transaction::setAccount);
            Optional.ofNullable(transactionEntity.getAmount()).ifPresent(transaction::setAmount);
            Optional.ofNullable(transactionEntity.getCategory()).ifPresent(transaction::setCategory);
            Optional.ofNullable(transactionEntity.getDate()).ifPresent(transaction::setDate);
            Optional.ofNullable(transactionEntity.getDescription()).ifPresent(transaction::setDescription);
            Optional.ofNullable(transactionEntity.getType()).ifPresent(transaction::setType);

            transactionEntity.setUpdatedDate(ZonedDateTime.now());

            return transactionRepository.save(transaction);

        }).orElseThrow(() -> new EntityNotFoundException("Transaction with id " + id + " not found"));
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public boolean isTransaction(Long id) {
        return transactionRepository.existsById(id);
    }

}

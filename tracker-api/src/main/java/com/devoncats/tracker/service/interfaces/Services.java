package com.devoncats.tracker.service.interfaces;

import com.devoncats.tracker.domain.entity.TransactionEntity;

import java.util.List;
import java.util.Optional;

public interface Services {

    TransactionEntity createTransaction(TransactionEntity transactionEntity);

    List<TransactionEntity> getAllTransactions();

    Optional<TransactionEntity> getTransactionById(Long id);

    List<TransactionEntity> getTransactionByDateMonthAfter(Integer month);

    TransactionEntity updateTransaction(TransactionEntity transactionEntity, Long id);

    void deleteTransaction(Long id);

    boolean isTransaction(Long id);

}

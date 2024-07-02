package com.devoncats.tracker.repository;

import com.devoncats.tracker.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE EXTRACT(MONTH FROM t.date) = ?1")
    List<TransactionEntity> findTransactionEntitiesByDateMonthAfter(int month);

    @Query("SELECT t FROM TransactionEntity t ORDER BY t.date")
    Iterable<TransactionEntity> findAllSortedByDate();

}

package com.devoncats.tracker.domain.entity;

import com.devoncats.tracker.domain.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    private Long id;

    private String description;

    private Double amount;

    private String category;

    private TransactionType type;

    private LocalDate date;

    private String account;

    private ZonedDateTime createdDate;

    private ZonedDateTime updatedDate;

}

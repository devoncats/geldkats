package com.devoncats.tracker.domain.dto;

import com.devoncats.tracker.domain.TransactionType;
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
public class TransactionDto {

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
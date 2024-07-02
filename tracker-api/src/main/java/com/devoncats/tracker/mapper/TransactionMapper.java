package com.devoncats.tracker.mapper;

import com.devoncats.tracker.mapper.interfaces.Mapper;
import com.devoncats.tracker.domain.dto.TransactionDto;
import com.devoncats.tracker.domain.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements Mapper<TransactionEntity, TransactionDto> {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionDto toDto(TransactionEntity transactionEntity) {
        return modelMapper.map(transactionEntity, TransactionDto.class);
    }

    @Override
    public TransactionEntity toEntity(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, TransactionEntity.class);
    }

}

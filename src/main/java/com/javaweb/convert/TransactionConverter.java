package com.javaweb.convert;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    public TransactionEntity toTransactionEntity(TransactionDTO transactionDTO) {
        if (transactionDTO.getId() != null){
            TransactionEntity transactionEntityOld = transactionRepository.findById(transactionDTO.getId())
                    .orElseThrow(() -> new ValidateDataException("Transaction is not found"));
        }
        TransactionEntity transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
        if (transactionDTO.getCode() != null) {
            transactionEntity.setTransactionName(transactionDTO.getCode());
        }
        return transactionEntity;
    }
}

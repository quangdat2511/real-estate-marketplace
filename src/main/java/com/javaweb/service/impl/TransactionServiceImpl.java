package com.javaweb.service.impl;

import com.javaweb.convert.TransactionConverter;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.Status;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Override
    public TransactionEntity createOrUpdateTransaction(TransactionDTO transactionDTO) {
        if (Status.getStatusName(transactionDTO.getCode()) == null)
            throw new ValidateDataException("Invalid transaction code");
        TransactionEntity transactionEntity = transactionConverter.toTransactionEntity(transactionDTO);
        transactionRepository.save(transactionEntity);
        return transactionEntity;
    }
    @Override
    public String delete(List<Long> ids) {
        List<TransactionEntity> transactionEntities = transactionRepository.findAllById(ids);
        if (transactionEntities.size() != ids.size()){
            throw new ValidateDataException("One or more building IDs are invalid!");
        }
        transactionRepository.deleteAllByIdIn(ids);
        return "success";
    }

    @Override
    public List<TransactionEntity> findByCustomerIdAndCode(Long customerId, String code) {
        return transactionRepository.findByCustomerIdAndTransactionName(customerId, code);
    }
}

package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionEntity createOrUpdateTransaction(TransactionDTO transactionDTO);
    String delete(List<Long> ids);
    List<TransactionEntity> findByCustomerIdAndCode(Long customerId, String code);
}

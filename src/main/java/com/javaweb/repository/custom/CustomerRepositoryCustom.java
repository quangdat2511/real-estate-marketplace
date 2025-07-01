package com.javaweb.repository.custom;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> getAllCustomers(CustomerSearchRequest customerSearchRequest);
    void deleteAllByIdIn(List<Long> Ids);
    int countTotalItem(CustomerSearchRequest customerSearchRequest);
}

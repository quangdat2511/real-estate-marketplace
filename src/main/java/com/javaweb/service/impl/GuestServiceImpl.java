package com.javaweb.service.impl;

import com.javaweb.convert.CustomerConverter;
import com.javaweb.convert.GuestConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.GuestDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GuestConverter guestConverter;
    @Override
    public CustomerEntity createGuest(GuestDTO guestDTO) {
        CustomerEntity existingCustomer = customerRepository.findByPhone(guestDTO.getPhone());
        if (existingCustomer != null)
            throw new ValidateDataException("Oops! phones must be unique – no duplicates allowed");
        CustomerEntity customerEntity = guestConverter.toCustomerEntity(guestDTO);
        customerRepository.save(customerEntity);
        return customerEntity;
    }


}

package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AssignmentCustomerServiceImpl implements AssignmentCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId())
                .orElseThrow(() -> new ValidateDataException("Customer is not found!"));;
        List<Long> staffIds = assignmentCustomerDTO.getStaffIds();
        customerEntity.getStaffs().clear();
        if (staffIds == null || staffIds.isEmpty()) {
            customerRepository.save(customerEntity);
            return;
        }
        if (staffIds.stream().anyMatch(Objects::isNull)) {
            throw new ValidateDataException("Staff ID in the list cannot be null!");
        }
        List<UserEntity> staffs = userRepository.findAllById(staffIds);
        if (staffs.size() != staffIds.size()) {
            throw new ValidateDataException((staffIds.size() - staffs.size()) +  " staff ID(s) are invalid!");
        }
        customerEntity.setStaffs(staffs);
        customerRepository.save(customerEntity);
    }
}

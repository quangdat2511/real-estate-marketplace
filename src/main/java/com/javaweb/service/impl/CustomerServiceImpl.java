package com.javaweb.service.impl;

import com.javaweb.convert.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest) {
        List<CustomerEntity> customerEntities = customerRepository.getAllCustomers(customerSearchRequest);
        List<CustomerSearchResponse> customerSearchResponses = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerSearchResponse customerSearchResponse = customerConverter.toCustomerResponseDTO(customerEntity);
            customerSearchResponses.add(customerSearchResponse);
        }
        return customerSearchResponses;
    }

    @Override
    public CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity existingCustomer = customerRepository.findByPhone(customerDTO.getPhone());
        boolean isUpdating = customerDTO.getId() != null;
        boolean phoneConflict = existingCustomer != null &&
                (!isUpdating || !existingCustomer.getId().equals(customerDTO.getId()));

        if (phoneConflict) {
            throw new ValidateDataException("Oops! phones must be unique – no duplicates allowed");
        }
        CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ValidateDataException("Customer with ID " + id + " not found"));
        return customerConverter.toCustomerDTO(customerEntity);
    }

    @Override
    public String delete(List<Long> ids) {
        List<CustomerEntity> customerEntities = customerRepository.findAllById(ids);
        if (customerEntities.size() != ids.size()){
            throw new ValidateDataException("One or more customer IDs are invalid!");
        }
        customerRepository.deleteAllByIdIn(ids);
        return "success";
    }

    @Override
    public List<StaffResponseDTO> getStaffByCustomerId(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ValidateDataException("Customer with ID " + id + " not found"));
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Set<Long> assignedStaffIds = customerEntity.getStaffs()
                .stream().map(UserEntity::getId).collect(Collectors.toSet());
        return getStaffResponseDTOS(staffList, assignedStaffIds);
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItem(customerSearchRequest);
    }

    private static List<StaffResponseDTO> getStaffResponseDTOS(List<UserEntity> staffList, Set<Long> assignedStaffIds) {
        return staffList.stream()
                .map(staff -> {
                    StaffResponseDTO dto = new StaffResponseDTO();
                    dto.setStaffId(staff.getId());
                    dto.setFullName(staff.getFullName());
                    dto.setChecked(assignedStaffIds.contains(staff.getId()) ? "checked" : "unchecked");
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAssignedStaff(Long customerId, Long staffId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        return customerEntity.getStaffs().stream().anyMatch(staff -> staff.getId().equals(staffId));
    }
}

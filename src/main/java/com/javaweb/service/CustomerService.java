package com.javaweb.service;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;


import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> getAllCustomers(CustomerSearchRequest customerSearchRequest);
    CustomerEntity createOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO findById(Long id);
    String delete(List<Long> ids);
    List<StaffResponseDTO> getStaffByCustomerId(Long id);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
    boolean checkAssignedStaff(Long customerId, Long staffId);
}

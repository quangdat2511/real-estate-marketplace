package com.javaweb.convert;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.Status;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerSearchResponse toCustomerResponseDTO(CustomerEntity customerEntity) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        String status = Status.getStatusName(customerEntity.getStatus());
        customerSearchResponse.setStatus(status);
        return customerSearchResponse;
    }
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntityOld = null;
        if (customerDTO.getId() != null){
            customerEntityOld = customerRepository.findById(customerDTO.getId())
                    .orElseThrow(() -> new ValidateDataException("Customer is not found"));
        }
        CustomerEntity customerEntityNew = modelMapper.map(customerDTO, CustomerEntity.class);
        if (customerEntityOld != null){
            customerEntityNew.setCreatedBy(customerEntityOld.getCreatedBy());
            customerEntityNew.setCreatedDate(customerEntityOld.getCreatedDate());
        }
        else{
            customerEntityNew.setCreatedBy(null);
            customerEntityNew.setCreatedDate(null);
        }
        customerEntityNew.setIsActive(true);
        return customerEntityNew;
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;

    }
}

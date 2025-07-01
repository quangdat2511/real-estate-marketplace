package com.javaweb.convert;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.GuestDTO;
import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GuestConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerEntity toCustomerEntity(GuestDTO guestDTO) {
        CustomerEntity customerEntityOld = null;
        if (guestDTO.getId() != null){
            customerEntityOld = customerRepository.findById(guestDTO.getId())
                    .orElseThrow(() -> new ValidateDataException("Guest is not found"));
        }
        CustomerEntity customerEntityNew = modelMapper.map(guestDTO, CustomerEntity.class);
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
}

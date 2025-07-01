package com.javaweb.api.admin;

import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> fieldErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Validate Failed");
            responseDTO.setData(fieldErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return null; // Không có lỗi
    }
    private ResponseEntity<?> saveCustomer(CustomerDTO customerDTO, BindingResult bindingResult, String successMessage) {
        ResponseEntity<?> errors = handleValidationErrors(bindingResult);
        if (errors != null) return errors;
        customerService.createOrUpdateCustomer(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(successMessage);
        responseDTO.setData(customerDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        return saveCustomer(customerDTO, bindingResult, "Create customer successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        return saveCustomer(customerDTO, bindingResult, "Update customer successfully");
    }
    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOList = customerService.getStaffByCustomerId(id);
        responseDTO.setMessage("Load staffs successfully");
        responseDTO.setData(staffResponseDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomers(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()){
            throw new ValidateDataException("No customer is selected to delete");
        }
        customerService.delete(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Delete customers successfully");
        responseDTO.setData(ids);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}

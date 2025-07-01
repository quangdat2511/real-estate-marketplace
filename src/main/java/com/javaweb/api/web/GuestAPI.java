package com.javaweb.api.web;

import com.javaweb.model.dto.GuestDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.GuestService;
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
@RequestMapping("/api/guests")
public class GuestAPI {
    @Autowired
    private GuestService guestService;
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
    private ResponseEntity<?> saveCustomer(GuestDTO guestDTO, BindingResult bindingResult) {
        ResponseEntity<?> errors = handleValidationErrors(bindingResult);
        if (errors != null) return errors;
        guestService.createGuest(guestDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Create customer successfully");
        responseDTO.setData(guestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody GuestDTO guestDTO, BindingResult bindingResult) {
        return saveCustomer(guestDTO, bindingResult);
    }

}

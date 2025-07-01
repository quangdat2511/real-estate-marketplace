package com.javaweb.api.admin;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign/customers")
public class AssignmentCustomerAPI {
    @Autowired
    private AssignmentCustomerService assignmentCustomerService;

    @PostMapping
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        // Validate buildingId
        if (assignmentCustomerDTO.getCustomerId() == null) {
            responseDTO.setMessage("Customer ID cannot be null");
            return ResponseEntity.badRequest().body(responseDTO);
        }


        // Call the service method
        assignmentCustomerService.updateAssignmentCustomer(assignmentCustomerDTO);

        responseDTO.setMessage("Assignment customer updated successfully");
        responseDTO.setData(assignmentCustomerDTO);

        return ResponseEntity.ok().body(responseDTO);
    }
}
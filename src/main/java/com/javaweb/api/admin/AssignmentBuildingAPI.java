package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign")
public class AssignmentBuildingAPI {
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public ResponseEntity<?> updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        // Validate buildingId
        if (assignmentBuildingDTO.getBuildingId() == null) {
            responseDTO.setMessage("Building ID cannot be null");
            return ResponseEntity.badRequest().body(responseDTO);
        }


        // Call the service method
        assignmentBuildingService.updateAssignmentBuilding(assignmentBuildingDTO);

        responseDTO.setMessage("Assignment building updated successfully");
        responseDTO.setData(assignmentBuildingDTO);

        return ResponseEntity.ok().body(responseDTO);
    }
}

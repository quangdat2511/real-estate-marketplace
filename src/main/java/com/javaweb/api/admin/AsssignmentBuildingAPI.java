package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign")
public class AsssignmentBuildingAPI {
    @PostMapping
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        if (assignmentBuildingDTO.getBuildingId() == null){
            responseDTO.setMessage("BuildingId can not be null");
            return ResponseEntity.badRequest().body(responseDTO);
        }
        // xuống service để xử lí
        responseDTO.setMessage("Assignment building updated successfully");
        return ResponseEntity.ok().body(responseDTO);
    }
}

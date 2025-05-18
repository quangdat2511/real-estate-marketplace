package com.javaweb.api.admin;

import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.BuildingService;
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
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

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
    private ResponseEntity<?> saveBuilding(BuildingDTO buildingDTO, BindingResult bindingResult, String successMessage) {
        ResponseEntity<?> errors = handleValidationErrors(bindingResult);
        if (errors != null) return errors;
        buildingService.createOrUpdateBuilding(buildingDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(successMessage);
        responseDTO.setData(buildingDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        return saveBuilding(buildingDTO, bindingResult, "Create building successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        return saveBuilding(buildingDTO, bindingResult, "Update building successfully");
    }
    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<StaffResponseDTO> staffResponseDTOList = buildingService.getStaffByBuildingId(id);
        responseDTO.setMessage("Load staffs successfully");
        responseDTO.setData(staffResponseDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuildings(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()){
            throw new ValidateDataException("No building is selected to delete");
        }
        buildingService.delete(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Delete buildings successfully");
        responseDTO.setData(ids);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}

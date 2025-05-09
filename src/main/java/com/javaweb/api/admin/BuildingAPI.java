package com.javaweb.api.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    private final UserRepository userRepository;
    @Autowired
    private BuildingService buildingService;

    public BuildingAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (bindingResult.hasErrors()) {
            List<String> fieldErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            responseDTO.setMessage("Validate Failed");
            responseDTO.setData(fieldErrors);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
        buildingService.createBuilding(buildingDTO);
        responseDTO.setMessage("Create building successfully");
        responseDTO.setData(buildingDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (bindingResult.hasErrors()) {
            List<String> fieldErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            responseDTO.setMessage("Validate Failed");
            responseDTO.setData(fieldErrors);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
        buildingService.updateBuilding(buildingDTO);
        responseDTO.setMessage("Update building successfully");
        responseDTO.setData(buildingDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaffs = userRepository.findByAssignmentBuildingEntities_BuildingEntity_Id(id);
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        for (UserEntity staff : staffList) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if (assignedStaffs.contains(staff)){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("unchecked");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setMessage("Load staffs successfully");
        responseDTO.setData(staffResponseDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuildings(@PathVariable List<Long> ids) {
        if (ids.isEmpty()){
            throw new ValidateDataException("No building is selected to delete");
        }
        buildingService.delete(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Delete buildings successfully");
        responseDTO.setData(ids);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}

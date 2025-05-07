package com.javaweb.api.admin;

import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.UserRepository;
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

    public BuildingAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ///... xuống service
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if (bindingResult.hasErrors()) {
                List<String> fieldErrors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                responseDTO.setMessage("Validate Failed");
                responseDTO.setData(fieldErrors);
                return ResponseEntity.badRequest().body(responseDTO);
            }
        }
        catch(Exception ex){
            responseDTO.setMessage("Internal Server Error");
            responseDTO.setData(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        System.out.println("ok");
        responseDTO.setMessage("Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult) {
        ///... xuống service
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            if (bindingResult.hasErrors()) {
                List<String> fieldErrors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                responseDTO.setMessage("Validate Failed");
                responseDTO.setData(fieldErrors);
                return ResponseEntity.badRequest().body(responseDTO);
            }
        }
        catch(Exception ex){
            responseDTO.setMessage("Internal Server Error");
            responseDTO.setData(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        System.out.println("ok");
        responseDTO.setMessage("Completed");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @GetMapping("/{id}/staffs")
    public ResponseEntity<?> loadStaffs(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO();
//        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//        List<UserEntity> assignedStaffs = new ArrayList<>();
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
//        for (UserEntity staff : staffList) {
//            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
//            staffResponseDTO.setStaffId(staff.getId());
//            staffResponseDTO.setFullName(staff.getFullName());
//            if (assignedStaffs.contains(staffResponseDTO)) {
//                staffResponseDTO.setChecked("checked");
//            }
//            else{
//                staffResponseDTO.setChecked("unchecked");
//            }
//            staffResponseDTOList.add(staffResponseDTO);
//        }
//        responseDTO.setMessage("Completed");
//        responseDTO.setData(staffResponseDTOList);
        StaffResponseDTO staff1 = new StaffResponseDTO();
        staff1.setStaffId(11L);
        staff1.setFullName("staff1");
        staff1.setChecked("checked");

        StaffResponseDTO staff2 = new StaffResponseDTO();
        staff2.setStaffId(12L);
        staff2.setFullName("staff2");
        staff2.setChecked("unchecked");
        responseDTO.setMessage("Completed");

        StaffResponseDTO staff3 = new StaffResponseDTO();
        staff3.setStaffId(13L);
        staff3.setFullName("staff3");
        staff3.setChecked(" checked");
        staffResponseDTOList.add(staff1);
        staffResponseDTOList.add(staff2);
        staffResponseDTOList.add(staff3);
        responseDTO.setMessage("Completed");
        responseDTO.setData(staffResponseDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteBuildings(@PathVariable List<Long> ids) {
        // xuoong service để xử lí
        System.out.println("ok");
        return null;
    }
}

package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        // Find the building entity or throw an exception if not found
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId())
                .orElseThrow(() -> new ValidateDataException("Building is not found!"));;
        List<Long> staffIds = assignmentBuildingDTO.getStaffIds();
        buildingEntity.getStaffs().clear();
        if (staffIds == null || staffIds.isEmpty()) {
            buildingRepository.save(buildingEntity);
            return;
        }
        if (staffIds.stream().anyMatch(Objects::isNull)) {
            throw new ValidateDataException("Staff ID in the list cannot be null!");
        }
        List<UserEntity> staffs = userRepository.findAllById(staffIds);
        if (staffs.size() != staffIds.size()) {
            throw new ValidateDataException((staffIds.size() - staffs.size()) +  " staff ID(s) are invalid!");
        }
        buildingEntity.getStaffs().addAll(staffs);
        buildingRepository.save(buildingEntity);
    }
}

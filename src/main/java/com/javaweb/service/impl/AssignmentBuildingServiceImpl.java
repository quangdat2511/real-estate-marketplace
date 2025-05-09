package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        // Find the building entity or throw an exception if not found
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId())
                .orElseThrow(() -> new ValidateDataException("Building is not found!"));

        // Remove existing assignments for the building
        assignmentBuildingRepository.deleteByBuildingEntity_Id(assignmentBuildingDTO.getBuildingId());

        List<Long> staffIds = assignmentBuildingDTO.getStaffIds();
        if (staffIds == null || staffIds.isEmpty()) {
            // No staff to assign, simply return
            return;
        }

        // Create a new list to hold assignment entities
        List<AssignmentBuildingEntity> assignmentBuildingEntities = new ArrayList<>();

        for (Long staffId : staffIds) {
            if (staffId == null) {
                // Skip null staff IDs
                throw new ValidateDataException("Staff ID in the list cannot be null!");
            }

            // Try to find user entity with the given staff ID
            UserEntity staff = userRepository.findById(staffId)
                    .orElseThrow(() -> new ValidateDataException("Staff with ID " + staffId + " is not found!"));

            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();

            assignmentBuildingEntity.setBuildingEntity(buildingEntity);
            assignmentBuildingEntity.setStaff(staff);

            // Add to the list of assignment entities
            assignmentBuildingEntities.add(assignmentBuildingEntity);
        }

        // Save all the new assignments
        assignmentBuildingRepository.saveAll(assignmentBuildingEntities);
    }
}

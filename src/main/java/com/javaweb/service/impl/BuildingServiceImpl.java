package com.javaweb.service.impl;

import com.javaweb.convert.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * Finds all buildings based on given search criteria.
     * @param buildingSearchRequest Contains filtering criteria for buildings
     * @return List of buildings matching the search criteria
     */
    public List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuildings(buildingSearchRequest);
        List<BuildingSearchResponse> results = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.toBuildingResponseDTO(buildingEntity);
            results.add(buildingSearchResponse);
        }
        return results;
    }

    @Override
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
        if (buildingEntity.getRentAreaEntities() != null){
            rentAreaRepository.deleteAll(buildingEntity.getRentAreaEntities());
        }
        if (buildingDTO.getRentArea() != null){
            String[] rentAreas = buildingDTO.getRentArea().split(",\\s*");
            for (String rentArea : rentAreas) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuildingEntity(buildingEntity);
                rentAreaEntity.setValue(Long.parseLong(rentArea));
                rentAreaRepository.save(rentAreaEntity);
            }
        }
        return buildingEntity;
    }
    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new ValidateDataException("Building with ID " + id + " not found"));
        return buildingConverter.toBuildingDTO(buildingEntity);
    }

    @Override
    public String delete(List<Long> ids) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAllById(ids);
        if (buildingEntities.size() != ids.size()){
            throw new ValidateDataException("One or more building IDs are invalid!");
        }
        rentAreaRepository.deleteAllByBuildingEntity_IdIn(ids);
        assignmentBuildingRepository.deleteAllByBuildingEntity_IdIn(ids);
        buildingRepository.deleteAllByIdIn(ids);
        return "success";
    }
    private List<StaffResponseDTO> getStaffResponseDTOS(List<UserEntity> staffList, Set<Long> assignedStaffIds) {
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        for (UserEntity staff : staffList) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(staff.getId());
            staffResponseDTO.setFullName(staff.getFullName());
            if (assignedStaffIds.contains(staff.getId())){
                staffResponseDTO.setChecked("checked");
            }
            else{
                staffResponseDTO.setChecked("unchecked");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        return staffResponseDTOList;
    }
    @Override
    public List<StaffResponseDTO> getStaffByBuildingId(Long id) {
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Set<Long> assignedStaffIds = userRepository.findByAssignmentBuildingEntities_BuildingEntity_Id(id)
                .stream()
                .map(UserEntity::getId)
                .collect(Collectors.toSet());
        return getStaffResponseDTOS(staffList, assignedStaffIds);
    }
}

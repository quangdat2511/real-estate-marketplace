package com.javaweb.service.impl;

import com.javaweb.convert.BuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BaseEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        List<Long> rentAreaIds = buildingEntities.stream().flatMap(buildingEntity -> buildingEntity.getRentAreaEntities().
                        stream()).map(BaseEntity::getId).collect(Collectors.toList());
        rentAreaRepository.deleteByIdIn(rentAreaIds);
        List<Long> assignmentBuildingIds = buildingEntities.stream().flatMap(buildingEntity -> buildingEntity.getAssignmentBuildingEntities().
                        stream()).map(AssignmentBuildingEntity::getId).collect(Collectors.toList());
        assignmentBuildingRepository.deleteByIdIn(assignmentBuildingIds);
        buildingRepository.deleteByIdIn(ids);
        return "success";
    }
}

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
    public BuildingEntity createBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
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
    public BuildingEntity updateBuilding(BuildingDTO buildingDTO) {
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
        for (Long id: ids){
            BuildingEntity buildingEntity = buildingRepository.findById(id).orElseThrow(() -> new ValidateDataException("Building is not found!"));
            List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();
            rentAreaRepository.deleteByIdIn(rentAreaEntities.stream().map(BaseEntity::getId).collect(Collectors.toList()));
            List<AssignmentBuildingEntity> asssignmentBuildingEntities = buildingEntity.getAssignmentBuildingEntities();
            assignmentBuildingRepository.deleteByIdIn((asssignmentBuildingEntities.stream().map(BaseEntity::getId).collect(Collectors.toList())));
            buildingRepository.deleteById(id);
        }
        return "success";
    }

}

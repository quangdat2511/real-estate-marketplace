package com.javaweb.convert;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javaweb.enums.District;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingRepository buildingRepository;
    // DTO -> entity
    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntityOld = null;
        if (buildingDTO.getId() != null){
            buildingEntityOld = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new ValidateDataException("Building is not found"));
        }
        BuildingEntity buildingEntityNew = modelMapper.map(buildingDTO, BuildingEntity.class);
        if (buildingEntityOld != null){
            buildingEntityNew.setStaffs(buildingEntityOld.getStaffs());
            buildingEntityNew.setCreatedDate(buildingEntityOld.getCreatedDate());
            buildingEntityNew.setCreatedBy(buildingEntityOld.getCreatedBy());
        }
        if (buildingDTO.getTypeCode() != null) {
            String typeAsString = String.join(", ", buildingDTO.getTypeCode());
            buildingEntityNew.setType(typeAsString);
        }
        return buildingEntityNew;
    }
    public BuildingSearchResponse toBuildingResponseDTO(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + "," + buildingEntity.getWard() + "," + District.getDistrictName(buildingEntity.getDistrict()));
        buildingSearchResponse.setAvailableArea(null);
        String rentAreaAsString = buildingEntity.getRentAreaEntities().stream().map(rentArea -> String.valueOf(rentArea.getValue())).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreaAsString);
        return buildingSearchResponse;
    }
    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        String rentAreaAsString = buildingEntity.getRentAreaEntities().stream().map(rentArea -> String.valueOf(rentArea.getValue())).collect(Collectors.joining(", "));
        buildingDTO.setRentArea(rentAreaAsString);
        buildingDTO.setTypeCode(Arrays.asList(buildingEntity.getType().split(",\\s*")));
        return buildingDTO;
    }
}

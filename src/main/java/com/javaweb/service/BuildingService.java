package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest);
    BuildingEntity createBuilding(BuildingDTO buildingDTO);
    BuildingEntity updateBuilding(BuildingDTO buildingDTO);
    BuildingDTO findById(Long id);
    String delete(List<Long> ids);
}

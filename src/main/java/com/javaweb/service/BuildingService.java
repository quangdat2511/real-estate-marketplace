package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest);
    BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO);
    BuildingDTO findById(Long id);
    String delete(List<Long> ids);
    List<StaffResponseDTO> getStaffByBuildingId(Long id);
    int countTotalItems(BuildingSearchRequest buildingSearchRequest);
    boolean checkAssignedStaff(Long buildingId, Long staffId);
}

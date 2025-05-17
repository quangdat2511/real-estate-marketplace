package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> getAllBuildings(BuildingSearchRequest buildingSearchRequest);
    int countTotalItem(BuildingSearchRequest buildingSearchRequest);
}

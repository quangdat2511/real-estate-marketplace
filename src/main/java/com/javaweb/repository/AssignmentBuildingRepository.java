package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByIdIn(List<Long> ids);
    void deleteByBuildingEntity_Id(Long buildingEntityId);
}

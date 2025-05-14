package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuildingEntity_Id(Long buildingId);
    void deleteAllByBuildingEntity_IdIn(List<Long> buildingIds);
}

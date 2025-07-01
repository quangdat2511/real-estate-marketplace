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
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
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
    private UserRepository userRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    public List<BuildingSearchResponse> getAllBuildings(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuildings(buildingSearchRequest);
        return buildingEntities.stream().map(buildingConverter::toBuildingResponseDTO).collect(Collectors.toList());
    }

    @Override
    public BuildingEntity createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity existingBuilding = buildingRepository.findByName(buildingDTO.getName());
        boolean isUpdating = buildingDTO.getId() != null;
        boolean nameConflict = existingBuilding != null &&
                (!isUpdating || !existingBuilding.getId().equals(buildingDTO.getId()));

        if (nameConflict) {
            throw new ValidateDataException("Oops! Building names must be unique – no duplicates allowed");
        }
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
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
        buildingRepository.deleteAllByIdIn(ids);
        return "success";
    }

    @Override
    public List<StaffResponseDTO> getStaffByBuildingId(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id)
                .orElseThrow(() -> new ValidateDataException("Building with ID " + id + " not found"));
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        Set<Long> assignedStaffIds = buildingEntity.getStaffs()
                .stream().map(UserEntity::getId).collect(Collectors.toSet());
        return getStaffResponseDTOS(staffList, assignedStaffIds);
    }
    private static List<StaffResponseDTO> getStaffResponseDTOS(List<UserEntity> staffList, Set<Long> assignedStaffIds) {
        return staffList.stream()
                .map(staff -> {
                    StaffResponseDTO dto = new StaffResponseDTO();
                    dto.setStaffId(staff.getId());
                    dto.setFullName(staff.getFullName());
                    dto.setChecked(assignedStaffIds.contains(staff.getId()) ? "checked" : "unchecked");
                    return dto;
                })
                .collect(Collectors.toList());
    }
    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        if (buildingDTO.getImageBase64() != null && !buildingDTO.getImageBase64().isEmpty()) {
            String path = "/building/" + buildingDTO.getImageName();

            // Xóa ảnh cũ nếu khác ảnh mới
            if (buildingEntity.getImage() != null && !path.equals(buildingEntity.getImage())) {
                File file = new File("C://home/office" + buildingEntity.getImage());
                file.delete();
            }

            // Ghi ảnh mới
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);

            // Gán ảnh mới
            buildingEntity.setImage(path);
        } else {
            // Không có ảnh mới: giữ nguyên ảnh cũ từ DTO
            // Nếu backend vẫn cần set lại, có thể dùng:
            if (buildingDTO.getImage() != null) {
                buildingEntity.setImage(buildingDTO.getImage());
            }
        }
    }

    @Override
    public int countTotalItems(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

    @Override
    public boolean checkAssignedStaff(Long buildingId, Long staffId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        return buildingEntity.getStaffs().stream().anyMatch(staff -> staff.getId().equals(staffId));
    }
}

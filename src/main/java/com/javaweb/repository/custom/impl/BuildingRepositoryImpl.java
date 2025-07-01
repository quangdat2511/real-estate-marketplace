package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    private void buildJoinClause(BuildingSearchRequest buildingSearchRequest, StringBuilder join) {
        if (buildingSearchRequest.getStaffId() != null) {
            join.append(" join assignmentbuilding asbd on asbd.buildingid = b.id");
        }
        if (buildingSearchRequest.getAreaFrom() != null || buildingSearchRequest.getAreaTo() != null) {
            join.append(" join rentarea on rentarea.buildingid = b.id");
        }
    }
    private void buildCondition(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                if (!key.equals("district") && !key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("area")
                        && !key.startsWith("rentPrice")) {
                    Object value = field.get(buildingSearchRequest);
                    if (value != null) {
                        if (StringUtils.isNumber(value.toString())) {
                            where.append(" AND b." + key + " = " + value.toString());
                        } else if (!value.toString().isEmpty()){
                            where.append(" AND b." + key + " Like '%" + value.toString() + "%'");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String district = buildingSearchRequest.getDistrict();
        if (district != null && !district.isEmpty()) {
            where.append(" AND b.district = '" + district + "'");
        }
        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" and asbd.staffId = " + staffId);
        }
        Long rentAreaFrom = buildingSearchRequest.getAreaFrom();
        Long rentAreaTo = buildingSearchRequest.getAreaTo();
        if (rentAreaFrom != null) {
            where.append(" AND rentarea.value >= " + rentAreaFrom);
        }
        if (rentAreaTo != null) {
            where.append(" AND rentarea.value <= " + rentAreaTo);
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" AND (");
            String likeConditions = typeCode.stream()
                    .map(code -> "b.type LIKE '%" + code + "%'")
                    .collect(Collectors.joining(" OR "));
            where.append(likeConditions);
            where.append(")");
        }

        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" AND b.rentPrice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" AND b.rentPrice <= " + rentPriceTo);
        }
    }

    @Override
    public List<BuildingEntity> getAllBuildings(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building as b ");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        buildJoinClause(buildingSearchRequest, sql);
        buildCondition(buildingSearchRequest, where);
        sql.append(where);
        sql.append(" order by b.createddate DESC");
        Pageable pageable = PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems());
        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {
        String sql = buildQueryFilter(buildingSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    private String buildQueryFilter(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building as b ");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        buildJoinClause(buildingSearchRequest, sql);
        buildCondition(buildingSearchRequest, where);
        sql.append(where);
        return sql.toString();
    }
}

package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private void buildJoinClause(CustomerSearchRequest customerSearchRequest, StringBuilder join) {
          if (customerSearchRequest.getStaffId() != null) {
              join.append(" join assignmentcustomer ascm on ascm.customerid= c.id");
          }
    }
    private void buildCondition(CustomerSearchRequest customerSearchRequest, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                if (!key.equals("staffId")) {
                    Object value = field.get(customerSearchRequest);
                    if (value != null) {
                        if (StringUtils.isNumber(value.toString())) {
                            where.append(" AND c." + key + " = " + value.toString());
                        } else if (!value.toString().isEmpty()){
                            where.append(" AND c." + key + " Like '%" + value.toString() + "%'");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" and ascm.staffid = " + staffId);
        }
    }
    @Override
    public List<CustomerEntity> getAllCustomers(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("select distinct c.* from customer c");
        StringBuilder where = new StringBuilder(" where c.is_active = 1");
        buildJoinClause(customerSearchRequest, sql);
        buildCondition(customerSearchRequest, where);
        sql.append(where);
        sql.append(" order by c.createddate desc");
        Pageable pageable = PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems());
        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public void deleteAllByIdIn(List<Long> Ids) {
        for (Long id : Ids) {
            CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, id);
            customerEntity.setIsActive(false);
        }
    }

    @Override
    public int countTotalItem(CustomerSearchRequest customerSearchRequest) {
        String sql = buildQueryFilter(customerSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    private String buildQueryFilter(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("select distinct c.* from customer c");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        buildJoinClause(customerSearchRequest, sql);
        buildCondition(customerSearchRequest, where);
        sql.append(where);
        return sql.toString();
    }
}

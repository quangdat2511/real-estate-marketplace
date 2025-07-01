package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaweb.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
    @Column(name = "fullname", nullable = false)
    private String fullName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "companyname")
    private String companyName;
    @Column(name = "demand")
    private String demand;
    @Column(name = "status")
    private String status;
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToMany
    @JoinTable(
            name ="assignmentcustomer",
            joinColumns = @JoinColumn(name="customerid"),
            inverseJoinColumns = @JoinColumn(name="staffid")
    )
    @JsonManagedReference
    private List<UserEntity> staffs;
}

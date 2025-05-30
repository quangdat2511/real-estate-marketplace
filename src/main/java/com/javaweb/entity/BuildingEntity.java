package com.javaweb.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "street")
    private String street;
    @Column(name = "ward")
    private String ward;
    @OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreaEntities;
    @OneToMany(mappedBy = "buildingEntity")
    private List<AssignmentBuildingEntity> assignmentBuildingEntities;
    @Column(name = "district", nullable = false)
    private String district;
    @Column(name = "structure")
    private String structure;
    @Column(name = "numberofbasement")
    private Long numberOfBasement;
    @Column(name = "floorarea")
    private Long floorArea;
    @Column(name = "direction")
    private String direction;
    @Column(name = "level")
    private String level;
    @Column(name = "rentprice", nullable = false)
    private Long rentPrice;
    @Column(name = "rentpricedescription")
    private String rentPriceDescription;
    @Column(name = "servicefee")
    private String serviceFee;
    @Column(name = "carfee")
    private String carFee;
    @Column(name = "motofee")
    private String motorbikeFee;
    @Column(name = "overtimefee")
    private String overtimeFee;
    @Column(name = "waterfee")
    private String waterFee;
    @Column(name = "electricityfee")
    private String electricityFee;
    @Column(name = "deposit")
    private String deposit;
    @Column(name = "payment")
    private String payment;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "decorationtime")
    private String decorationTime;
    @Column(name = "brokeragefee")
    private Double brokerageFee;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "note")
    private String note;
    @Column(name = "linkofbuilding")
    private String linkOfBuilding;
    @Column(name = "map")
    private String map;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
}

package com.javaweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {
    @Column(name = "value")
    private Long value;
    @ManyToOne
    @JoinColumn(name = "buildingid")
    @JsonBackReference
    private BuildingEntity buildingEntity;
}
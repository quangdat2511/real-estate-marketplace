package com.javaweb.model.dto;



import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO{
    @NotBlank(message = "Building name must not be blank")
    private String name;
    private String street;
    private String ward;
    @NotBlank(message = "District must not be blank")
    private String district;
    private String structure;
    private Long numberOfBasement;
    private Long floorArea;
    private String direction;
    private String level;
    @NotNull(message = "Rent price must not be null")
    @Min(value = 5, message = "Rent price must >= 5")
    private Long rentPrice;
    private String rentPriceDescription;
    private String serviceFee;
    private String carFee;
    private String motobikeFee;
    private String overtimeFee;
    private String waterFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private double brokerageFee;
    private String note;
    private String linkOfBuilding;
    private String map;
    private String image;
    private String imageBase64;
    private String imageName;
    private String managerName;
    private String managerPhoneNumber;
    @Size(min = 1, message = "Building type is required")
    private List<String> typeCode;
    private String rentArea;
}
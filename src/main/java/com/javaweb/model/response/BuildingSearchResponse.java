package com.javaweb.model.response;


import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BuildingSearchResponse extends AbstractDTO {
	private String name;
	private String address;
	private Long numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	private Long floorArea;
	private Long availableArea;
	private String rentArea;
	private Long rentPrice;
	private String serviceFee;
	private Double brokerageFee;
}

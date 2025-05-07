package com.javaweb.controller.admin;



import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.ModalExclude;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @GetMapping("/admin/building-list")
    public ModelAndView getAllBuildings(@ModelAttribute BuildingSearchRequest buildingSearchRequest, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        //Project2...
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        buildingSearchResponse.setId(2L);
        buildingSearchResponse.setName("quangdat");
        buildingSearchResponse.setAddress("TO HIEN THANh");
        buildingSearchResponse.setRentArea("111, 222, 333");
        buildingSearchResponse.setNumberOfBasement(2L);
        buildingSearchResponse.setManagerPhoneNumber("113");
        buildingSearchResponse.setManagerName("Anh Thiện");
        buildingSearchResponse.setRentPrice(200L);
        BuildingSearchResponse buildingSearchResponse2 = new BuildingSearchResponse();
        buildingSearchResponse2.setId(3L);
        buildingSearchResponse2.setName("quangdat");
        buildingSearchResponse2.setAddress("TO HIEN THANh");
        buildingSearchResponse2.setRentArea("111, 222, 333");
        buildingSearchResponse2.setNumberOfBasement(2L);
        buildingSearchResponse2.setManagerPhoneNumber("113");
        buildingSearchResponse2.setManagerName("Anh Thiện");
        buildingSearchResponse2.setRentPrice(200L);
        buildingSearchResponses.add(buildingSearchResponse);
        buildingSearchResponses.add(buildingSearchResponse2);
        modelAndView.addObject("buildingSearchResponses", buildingSearchResponses);
        return modelAndView;
    }
    @GetMapping("/admin/building-edit")
    public ModelAndView createBuilding(@ModelAttribute BuildingDTO buildingDTO){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }
    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        //findById dưới service và convert qua DTO
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setDistrict("QUAN_2");
        buildingDTO.setName("quangdat");
        buildingDTO.setStreet("TO HIEN THANh");
        buildingDTO.setRentArea("111, 222, 333");
        buildingDTO.setRentPrice(200L);
        List<String> typeCode = new ArrayList<>();
        typeCode.add("TANG_TRET");
        typeCode.add("NOI_THAT");
        buildingDTO.setTypeCode(typeCode);
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }
}

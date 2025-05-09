package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/admin/building-list")
    public ModelAndView getAllBuildings(@ModelAttribute BuildingSearchRequest buildingSearchRequest) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        //Project2...
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.getAllBuildings(buildingSearchRequest);
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
        BuildingDTO buildingDTO = buildingService.findById(id);
//        buildingDTO.setId(id);
//        buildingDTO.setDistrict("QUAN_2");
//        buildingDTO.setName("quangdat");
//        buildingDTO.setStreet("TO HIEN THANh");
//        buildingDTO.setRentArea("111, 222, 333");
//        buildingDTO.setRentPrice(200L);
//        List<String> typeCode = new ArrayList<>();
//        typeCode.add("TANG_TRET");
//        typeCode.add("NOI_THAT");
//        buildingDTO.setTypeCode(typeCode);
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }
}

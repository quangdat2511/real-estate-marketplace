package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private UserService userService;
    @Autowired
    private BuildingService buildingService;
    @GetMapping("/admin/building-list")
    public ModelAndView getAllBuildings(@ModelAttribute(SystemConstant.MODEL)  BuildingSearchRequest buildingSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        modelAndView.addObject("modelSearch", buildingSearchRequest);
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        //Project2...
        DisplayTagUtils.of(request, buildingSearchRequest);
        if (SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
        }
        List<BuildingSearchResponse> buildingSearchResponses = buildingService.getAllBuildings(buildingSearchRequest);
        buildingSearchRequest.setListResult(buildingSearchResponses);
        buildingSearchRequest.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));
        modelAndView.addObject("buildingSearchResponses", buildingSearchRequest);
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
    public ModelAndView updateBuilding(@PathVariable Long id, Model model){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        //findById dưới service và convert qua DTO
        BuildingDTO buildingDTO = buildingService.findById(id);
        modelAndView.addObject("buildingEdit", buildingDTO);
        if (SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if (!buildingService.checkAssignedStaff(id, staffId)){
                modelAndView.setViewName("/error/404");
                return modelAndView;
            }
        }
        modelAndView.addObject("district", District.getDistrict());
        modelAndView.addObject("type", RentType.getType());
        return modelAndView;
    }
}

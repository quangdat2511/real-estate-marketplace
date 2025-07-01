package com.javaweb.controller.admin;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.RentType;
import com.javaweb.enums.Status;
import com.javaweb.enums.Transaction;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
import com.javaweb.service.TransactionService;
import com.javaweb.service.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/admin/customer-list")
    public ModelAndView getAllCustomers(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
        modelAndView.addObject("modelSearch", customerSearchRequest);
        //Project2...
        if (SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
        }
        modelAndView.addObject("status", Status.getStatus());
        modelAndView.addObject("staffs", userService.getStaffs());
        List<CustomerSearchResponse> customerSearchResponses = customerService.getAllCustomers(customerSearchRequest);
        customerSearchRequest.setListResult(customerSearchResponses);
        customerSearchRequest.setTotalItems(customerService.countTotalItems(customerSearchRequest));
        modelAndView.addObject("customerSearchResponses", customerSearchRequest);
        return modelAndView;
    }
    @GetMapping("/admin/customer-edit")
    public ModelAndView createCustomer(@ModelAttribute CustomerDTO customerDTO, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        modelAndView.addObject("customerEdit", customerDTO);
        modelAndView.addObject("status", Status.getStatus());
        modelAndView.addObject("transactionName", Transaction.getTransaction());
        List<TransactionEntity> CSKH = new ArrayList<>();
        List<TransactionEntity> DDX = new ArrayList<>();
        modelAndView.addObject("CSKH", CSKH);
        modelAndView.addObject("DDX", DDX);
        return modelAndView;
    }
    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable Long id, Model model){
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        if (SecurityUtils.getAuthorities().contains(SystemConstant.ADMIN_ROLE)){
            Long staffId = SecurityUtils.getPrincipal().getId();
            if (!customerService.checkAssignedStaff(id, staffId)){
                modelAndView.setViewName("/error/404");
                return modelAndView;
            }
        }
        //findById dưới service và convert qua DTO
        CustomerDTO customerDTO = customerService.findById(id);
        modelAndView.addObject("customerEdit", customerDTO);
        modelAndView.addObject("status", Status.getStatus());
        modelAndView.addObject("transactionName", Transaction.getTransaction());
        List<TransactionEntity> CSKH = transactionService.findByCustomerIdAndCode(id, "CSKH");
        List<TransactionEntity> DDX = transactionService.findByCustomerIdAndCode(id, "DDX");
        modelAndView.addObject("CSKH", CSKH);
        modelAndView.addObject("DDX", DDX);
        return modelAndView;
    }


}

package com.javaweb.api.admin;

import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/registers")
public class RegisterAPI {
    @Autowired
    private UserService userService;
    private ResponseEntity<?> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> fieldErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Validate Failed");
            responseDTO.setData(fieldErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        return null; // Không có lỗi
    }
    private ResponseEntity<?> saveNewUser(UserDTO newUser, BindingResult bindingResult) {
        ResponseEntity<?> errors = handleValidationErrors(bindingResult);
        if (errors != null) return errors;
        userService.insert(newUser);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Đăng ký tài khoản thành công");
        responseDTO.setData(newUser);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO newUser, BindingResult bindingResult) {
        newUser.setRoleCode("USER");
        if (userService.existsByUserName(newUser.getUserName())) {
            throw new ValidateDataException("Username đã tồn tại trong hệ thống.");
        }
        return saveNewUser(newUser, bindingResult);
    }
}

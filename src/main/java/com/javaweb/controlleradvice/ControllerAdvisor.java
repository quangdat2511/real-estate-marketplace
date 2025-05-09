package com.javaweb.controlleradvice;

import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status, String detail){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setDetail(detail);
        return new ResponseEntity<>(responseDTO, status);
    }
    @ExceptionHandler(ValidateDataException.class)
    public ResponseEntity<Object> handleValidateDataException(ValidateDataException ex){
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, "Dữ liệu không hợp lệ");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi nội bộ");
    }
}

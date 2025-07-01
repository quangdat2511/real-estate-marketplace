package com.javaweb.api.admin;

import com.javaweb.exception.ValidateDataException;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;
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
    private ResponseEntity<?> saveTransaction(TransactionDTO transactionDTO, BindingResult bindingResult, String successMessage) {
        ResponseEntity<?> errors = handleValidationErrors(bindingResult);
        if (errors != null) return errors;
        transactionService.createOrUpdateTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(successMessage);
        responseDTO.setData(transactionDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        return saveTransaction(transactionDTO, bindingResult, "Create transaction successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateTransaction(@Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        return saveTransaction(transactionDTO, bindingResult, "Update transaction successfully");
    }
    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteTransactions(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()){
            throw new ValidateDataException("No transaction is selected to delete");
        }
        transactionService.delete(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Delete transactions successfully");
        responseDTO.setData(ids);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}

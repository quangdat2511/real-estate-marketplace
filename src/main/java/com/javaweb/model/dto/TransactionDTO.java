package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionDTO extends AbstractDTO{
    private String code;
    @NotBlank(message = "note must not be blank")
    private String note;
    @NotNull(message = "customerId must not be null")
    @Min(value = 1, message = "customerId must >= 1")
    private Long customerId;
}

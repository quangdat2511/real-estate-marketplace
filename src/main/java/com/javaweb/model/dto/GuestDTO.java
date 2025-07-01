package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GuestDTO extends AbstractDTO{
    @NotBlank(message = "fullName must not be blank")
    private String fullName;
    @NotBlank(message = "phone must not be blank")
    private String phone;
    private String email;
}

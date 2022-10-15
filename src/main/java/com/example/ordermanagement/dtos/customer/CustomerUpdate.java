package com.example.ordermanagement.dtos.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerUpdate implements Serializable {

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    private String registrationCode;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String telephone;


}


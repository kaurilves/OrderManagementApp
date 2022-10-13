package com.example.ordermanagement.dtos.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerCreate implements Serializable {

    private String fullName;
    private String registrationCode;
    private String email;
    private String telephone;

}


package com.example.ordermanagement.dtos.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerUpdate implements Serializable {
        private String fullName;
        private Integer registrationCode;
        private String email;
        private String telephone;

    }


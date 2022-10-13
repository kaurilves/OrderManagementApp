package com.example.ordermanagement.dtos.customer;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
        private Integer id;
        private String fullName;
        private Integer registrationCode;
        private String email;
        private String telephone;
}

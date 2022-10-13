package com.example.ordermanagement.dtos.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
        private Integer id;
        private String fullName;
        private Integer registrationCode;
        private String email;
        private String telephone;
}

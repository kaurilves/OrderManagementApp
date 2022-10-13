package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.dtos.customer.Customer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Order implements Serializable {
        private Integer id;
        private LocalDate date;
        private Customer customer;
        private String email;
        private String telephone;
}

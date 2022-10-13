package com.example.ordermanagement.dtos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

public class Customer {
        private Integer id;
        private String fullName;
        private Integer registrationCode;
        private String email;
        private String telephone;

}

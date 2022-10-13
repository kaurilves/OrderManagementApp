package com.example.ordermanagement.dtos.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Product implements Serializable {
        private Integer id;
        private String name;
        private String skuCode;
        private BigDecimal price;
}

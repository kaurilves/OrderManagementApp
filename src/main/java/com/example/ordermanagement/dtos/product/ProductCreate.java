package com.example.ordermanagement.dtos.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductCreate implements Serializable {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String skuCode;

    @NotNull
    private BigDecimal price;
}


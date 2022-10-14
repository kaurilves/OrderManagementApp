package com.example.ordermanagement.dtos.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductUpdate implements Serializable {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String skuCode;

    @NotNull
    @Positive
    private BigDecimal price;
}


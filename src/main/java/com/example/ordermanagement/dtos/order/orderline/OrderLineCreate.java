package com.example.ordermanagement.dtos.order.orderline;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class OrderLineCreate {

    @NotNull
    private Integer  productId;

    @NotNull
    @Positive
    private Integer quantity;

}


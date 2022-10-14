package com.example.ordermanagement.dtos.order.orderline;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLine {
    private Integer orderLineId;
    private String productName;
    private String skuCode;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal orderLineSum;
}

package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.dtos.order.orderline.OrderLine;
import com.example.ordermanagement.dtos.order.orderline.OrderLineUpdate;
import com.example.ordermanagement.entities.OrderLineEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderUpdate implements Serializable {

    @NotNull
    private Integer orderId;

    @NotNull
    private Integer customerId;

    @NotNull
    private LocalDate date;

    @NotNull
    @NotEmpty
    @NotBlank
    private List<OrderLineUpdate> updatedOrderLines;
}



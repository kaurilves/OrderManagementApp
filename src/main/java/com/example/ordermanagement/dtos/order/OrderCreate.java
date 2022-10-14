package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.dtos.order.orderline.OrderLineCreate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderCreate implements Serializable {

    @NotNull
    private Integer customerId;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private LocalDate date;

    @NotNull
    @NotEmpty
    @NotBlank
    private List<OrderLineCreate> createdOrderLines;

}


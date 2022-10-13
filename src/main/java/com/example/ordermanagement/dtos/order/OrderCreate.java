package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.entities.OrderLine;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderCreate implements Serializable {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private LocalDate orderDate;

    @NotNull
    private List<OrderLine> orderLines;

}


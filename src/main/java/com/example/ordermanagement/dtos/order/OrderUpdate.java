package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.entities.OrderLine;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderUpdate implements Serializable {

    @NotNull
    private List<OrderLine> orderLines;
}



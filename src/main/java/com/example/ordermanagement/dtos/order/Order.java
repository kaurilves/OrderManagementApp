package com.example.ordermanagement.dtos.order;

import com.example.ordermanagement.dtos.order.orderline.OrderLine;
import com.example.ordermanagement.entities.OrderLineEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class Order implements Serializable {

        private Integer id;
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
        private LocalDate date;
        private List<OrderLine> orderLines;

}

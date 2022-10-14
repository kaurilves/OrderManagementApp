package com.example.ordermanagement.mappers;


import com.example.ordermanagement.dtos.order.orderline.OrderLine;
import com.example.ordermanagement.dtos.order.orderline.OrderLineCreate;
import com.example.ordermanagement.entities.OrderLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderLineMapper {

    OrderLineEntity orderLineCreateToOrderLineEntity(OrderLineCreate orderLineCreate);

    @Mapping(source = "id", target = "orderLineId")
    OrderLine orderLineEntityToOrderLine(OrderLineEntity orderLineEntity);

    List<OrderLine> orderLineEntitiesToOrderLines(List<OrderLineEntity> orderLinesByOrderId);
}
